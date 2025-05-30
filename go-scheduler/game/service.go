package game

import (
	"math/rand"
	"scheduler/database"
	"scheduler/functions"
	"scheduler/game/strategy"
	"scheduler/model"
	"time"

	"gorm.io/gorm"
)

type GameService struct {
	db *gorm.DB
}

func NewGameService(db *gorm.DB) *GameService {
	return &GameService{db: db}
}

func (s *GameService) GameResult() {
	var jogo model.Jogo

	tx := s.db.Begin()

	if err := tx.Where("dt_fim < ? AND status = ?", time.Now(), "ABERTO").First(&jogo).Error; err != nil {
		if err != gorm.ErrRecordNotFound {
			functions.SetErrorLog(err, "resultado")
		}
	}

	numeros := generateNumeros()
	numerosJSONB := functions.Int64SliceToJSONB(numeros)

	resultado := model.Resultado{
		ID:      getIdResultado(),
		Data:    time.Now(),
		Numeros: numerosJSONB,
		JogoID:  jogo.ID,
	}

	if err := tx.Create(&resultado).Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
	}

	if err := tx.Model(&model.Jogo{}).Where("id = ?", jogo.ID).Update("status", "FECHADO").Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
	}

	if err := s.premiar(tx, jogo.ID, numeros); err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
	}

	if err := tx.Commit().Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "resultado")
	}

	functions.SetResultadoLog(resultado, jogo)
}

func (s *GameService) premiar(tx *gorm.DB, jogoID int64, numeros []int64) error {
	var apostas []model.Aposta
	if err := tx.Where("jogo_id = ?", jogoID).Find(&apostas).Error; err != nil {
		return err
	}

	var bichos []model.Bicho
	if err := tx.Find(&bichos).Error; err != nil {
		return err
	}

	for _, aposta := range apostas {
		numsAposta, err := functions.StringToInt64Slice(aposta.Numeros)
		if err != nil {
			return err
		}

		strategyImpl, err := strategy.GetStrategy(model.TipoAposta(aposta.Tipo))
		if err != nil {
			return err
		}

		premio := strategyImpl.CalcularPremio(aposta.Valor, numsAposta, numeros, bichos)
		premio = functions.RoundToTwoDecimalPlaces(premio)

		if err := tx.Model(&model.Aposta{}).Where("id = ?", aposta.ID).Update("premio", premio).Error; err != nil {
			return err
		}

		var apostador model.Apostador
		if err := tx.Where("id = ?", aposta.ApostadorID).First(&apostador).Error; err != nil {
			return err
		}

		if premio > 0 {
			if apostador.Divida > 0 {
				if premio >= apostador.Divida {
					premio -= apostador.Divida
					apostador.Divida = 0
				} else {
					apostador.Divida -= premio
					premio = 0
				}
			}
			apostador.Saldo += premio
			if err := tx.Save(&apostador).Error; err != nil {
				return err
			}
		}
	}

	return nil
}

func generateNumeros() []int64 {
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

func getIdResultado() int64 {
	var resultado model.Resultado
	database.DB.Model(&model.Resultado{}).Order("id DESC").Limit(1).Find(&resultado)
	if resultado.ID == 0 {
		return 1
	}
	return resultado.ID + 1
}
