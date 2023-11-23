package game

import (
	"math/rand"
	"scheduler/database"
	"scheduler/functions"
	"scheduler/model"
	"time"

	"gorm.io/gorm"
)

func GenerateNumeros() []int64 {
	seen := make(map[int64]bool)
	numeros := make([]int64, 0, 5)

	for len(numeros) < 5 {
		newNum := rand.Int63n(9999)
		lastTwoDigits := newNum % 100

		if !seen[lastTwoDigits] {
			numeros = append(numeros, newNum)
			seen[lastTwoDigits] = true
		}
	}

	return numeros
}

func Int64SliceToJSONB(numbers []int64) model.JSONB {
	var jsonb model.JSONB
	for _, num := range numbers {
		jsonb = append(jsonb, num)
	}
	return jsonb
}

func GetIdResultado() int64 {
	var resultado model.Resultado
	database.DB.Model(&model.Resultado{}).Order("id DESC").Limit(1).Find(&resultado)
	if resultado.ID == 0 {
		return 1
	}
	return resultado.ID + 1

}

func GameResult() {
	var jogo model.Jogo

	tx := database.DB.Begin()

	if err := tx.Where("dt_fim < ? AND status = ?", time.Now(), "ABERTO").First(&jogo).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			tx.Rollback()
			return
		}
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	numeros := GenerateNumeros()
	numerosJSONB := Int64SliceToJSONB(numeros)

	newResultado := model.Resultado{
		ID:      GetIdResultado(),
		Data:    time.Now(),
		Numeros: numerosJSONB,
		JogoID:  jogo.ID,
	}

	if err := tx.Create(&newResultado).Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	if err := tx.Model(&model.Jogo{}).Where("id = ?", jogo.ID).Update("status", "FECHADO").Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	if err := tx.Commit().Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}
	functions.SetResultadoLog(newResultado, jogo)
}
