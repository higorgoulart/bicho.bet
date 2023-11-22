package game

import (
	"fmt"
	"math/rand"
	"scheduler/database"
	"scheduler/model"
	"time"

	"gorm.io/gorm"
)

func GenerateNumeros() []int64 {
	var numeros []int64
	for i := 0; i < 5; i++ {
		numeros = append(numeros, rand.Int63n(9999))
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

func Game() {
	var jogo model.Jogo

	tx := database.DB.Begin()

	if err := tx.Where("status = ?", "ABERTO").First(&jogo).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			tx.Rollback()
			fmt.Println("Não há jogos abertos")
			return
		}
		tx.Rollback()
		fmt.Println("Erro ao buscar jogo:", err)
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
		fmt.Println("Erro ao criar resultado:", err)
		return
	}

	if err := tx.Model(&model.Jogo{}).Where("id = ?", jogo.ID).Update("status", "FECHADO").Error; err != nil {
		tx.Rollback()
		fmt.Println("Erro ao atualizar status do jogo:", err)
		return
	}

	if err := tx.Commit().Error; err != nil {
		tx.Rollback()
		fmt.Println("Erro ao realizar commit de transação:", err)
		return
	}
}
