package game

import (
	"math/rand"
	"scheduler/database"
	"scheduler/functions"
	"scheduler/model"
	"time"

	"gorm.io/gorm"
)

func GameResult() {
	var jogo model.Jogo

	db := database.DB.Begin()

	if err := db.Where("dt_fim < ? AND status = ?", time.Now(), "ABERTO").First(&jogo).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			db.Rollback()
			return
		}
		db.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	numeros := generateNumeros()
	numerosJSONB := functions.Int64SliceToJSONB(numeros)

	newResultado := model.Resultado{
		ID:      getIdResultado(),
		Data:    time.Now(),
		Numeros: numerosJSONB,
		JogoID:  jogo.ID,
	}

	if err := db.Create(&newResultado).Error; err != nil {
		db.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	if err := db.Model(&model.Jogo{}).Where("id = ?", jogo.ID).Update("status", "FECHADO").Error; err != nil {
		db.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	if err := premiar(db, jogo.ID, numeros); err != nil {
		db.Rollback()
		if err == gorm.ErrRecordNotFound {
			return
		}
		functions.SetErrorLog(err, "resultado")
		return
	}

	if err := db.Commit().Error; err != nil {
		db.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	functions.SetResultadoLog(newResultado, jogo)
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

func premiar(db *gorm.DB, idJogo int64, numeros []int64) error {
	var apostas []model.Aposta
	if err := db.Where("jogo_id = ?", idJogo).Find(&apostas).Error; err != nil {
		return err
	}

	var bichos []model.Bicho
	if err := db.Find(&bichos).Error; err != nil {
		return err
	}

	for _, aposta := range apostas {
		numerosAposta, err := functions.StringToInt64Slice(aposta.Numeros)

		if err != nil {
			return err
		}

		premio := obterPremio(aposta.Valor, model.TipoAposta(aposta.Tipo), numerosAposta, numeros, bichos)
		premio = functions.RoundToTwoDecimalPlaces(premio)
		if err := db.Model(&model.Aposta{}).Where("id = ?", aposta.ID).Update("premio", premio).Error; err != nil {
			return err
		}

		var apostador model.Apostador

		if err := db.Where("id = ?", aposta.ApostadorID).First(&apostador).Error; err != nil {
			return err
		}

		if premio > 0 {
			if apostador.Divida > 0 {
				if premio >= apostador.Divida {
					premio = premio - apostador.Divida
					apostador.Divida = 0.0
				} else {
					apostador.Divida = apostador.Divida - premio
					premio = 0.0
				}
			}

			if premio > 0 {
				apostador.Saldo = apostador.Saldo + premio
			}

			db.Save(&apostador)
		}
	}

	return nil
}

func obterPremio(valor float64, tipo model.TipoAposta, apostas []int64, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := obterPosicoesCorretas(tipo, apostas, resultados, bichos)
	multiplicador := obterMultiplicador(tipo, posicoes)

	return valor * multiplicador
}

func obterPosicoesCorretas(tipo model.TipoAposta, apostas []int64, resultados []int64, bichos []model.Bicho) []model.TipoResultado {
	var posicoes []model.TipoResultado

	i := 0

	for _, resultado := range resultados {
		for _, apostado := range apostas {
			var acerto bool

			if tipo == model.DEZENA || tipo == model.CENTENA || tipo == model.MILHAR {
				if apostado < 100 {
					acerto = functions.FormatNumber(resultado, 2) == functions.FormatNumber(apostado, 2)
				} else if apostado < 1000 {
					acerto = functions.FormatNumber(resultado, 3) == functions.FormatNumber(apostado, 3)
				} else {
					acerto = resultado == apostado
				}
			} else {
				acerto = obterBicho(bichos, resultado).ID == apostado
			}

			if acerto {
				index := i + 1
				tipoResultado := model.TipoResultado(index)
				posicoes = append(posicoes, tipoResultado)
			} else if tipo == model.DEZENA || tipo == model.CENTENA || tipo == model.MILHAR {
				bichoApostado := obterBicho(bichos, apostado)
				bichoResultado := obterBicho(bichos, resultado)

				if bichoApostado.ID == bichoResultado.ID {
					posicoes = append(posicoes, model.BICHO)
				}
			}
		}

		i++
	}

	return posicoes
}

func obterBicho(bichos []model.Bicho, number int64) model.Bicho {
	for _, bicho := range bichos {
		numeros, err := functions.StringToInt64Slice(bicho.Numeros)

		if err != nil {
			if err == gorm.ErrRecordNotFound {
				continue
			}
			functions.SetErrorLog(err, "resultado")
			continue
		}

		for _, numero := range numeros {
			if functions.FormatNumber(numero, 2) == functions.FormatNumber(number, 2) {
				return bicho
			}
		}
	}

	return model.Bicho{}
}

func obterMultiplicador(tipo model.TipoAposta, posicoes []model.TipoResultado) float64 {
	if len(posicoes) == 0 {
		return 0.0
	}

	switch tipo {
	case model.GRUPO:
		return obterMultiplicadorGrupo(posicoes)
	case model.DUQUE:
		return obterMultiplicadorDuque(posicoes)
	case model.TERNO:
		return obterMultiplicadorTerno(posicoes)
	case model.QUADRA:
		return obterMultiplicadorQuadra(posicoes)
	case model.QUINA:
		return obterMultiplicadorQuina(posicoes)
	case model.DEZENA:
		return obterMultiplicadorDezena(posicoes)
	case model.CENTENA:
		return obterMultiplicadorCentena(posicoes)
	case model.MILHAR:
		return obterMultiplicadorMilhar(posicoes)
	default:
		return 0.0
	}
}

func obterMultiplicadorGrupo(posicoes []model.TipoResultado) float64 {
	if functions.Contains(posicoes, model.PRIMEIRA) {
		return 12.0
	}
	return 3.0
}

func obterMultiplicadorDuque(posicoes []model.TipoResultado) float64 {
	if functions.ContainsAll(posicoes, model.PRIMEIRA, model.SEGUNDA) {
		return 95.0
	} else if len(posicoes) == 2 {
		return 12.0
	}
	return 1.0
}

func obterMultiplicadorTerno(posicoes []model.TipoResultado) float64 {
	if functions.ContainsAll(posicoes, model.PRIMEIRA, model.SEGUNDA, model.TERCEIRA) {
		return 700.0
	} else if len(posicoes) == 3 {
		return 42.0
	} else if len(posicoes) == 2 {
		return 3.0
	}
	return 0.75
}

func obterMultiplicadorQuadra(posicoes []model.TipoResultado) float64 {
	if functions.ContainsAll(posicoes, model.PRIMEIRA, model.SEGUNDA, model.TERCEIRA, model.QUARTA) {
		return 4000.0
	} else if len(posicoes) == 4 {
		return 500.0
	} else if len(posicoes) == 3 {
		return 22.0
	} else if len(posicoes) == 2 {
		return 1.5
	}
	return 0.2
}

func obterMultiplicadorQuina(posicoes []model.TipoResultado) float64 {
	if functions.ContainsAll(posicoes, model.PRIMEIRA, model.SEGUNDA, model.TERCEIRA, model.QUARTA, model.QUINTA) {
		return 17000.0
	} else if len(posicoes) == 4 {
		return 150.0
	} else if len(posicoes) == 3 {
		return 8.0
	} else if len(posicoes) == 2 {
		return 1.0
	}
	return 0.2
}

func obterMultiplicadorDezena(posicoes []model.TipoResultado) float64 {
	return obterMultiplicadorNumerico(posicoes, 50.0, 7.0)
}

func obterMultiplicadorCentena(posicoes []model.TipoResultado) float64 {
	return obterMultiplicadorNumerico(posicoes, 500.0, 60.0)
}

func obterMultiplicadorMilhar(posicoes []model.TipoResultado) float64 {
	return obterMultiplicadorNumerico(posicoes, 5000.0, 600.0)
}

func obterMultiplicadorNumerico(posicoes []model.TipoResultado, valorPrimeiro, valorOutro float64) float64 {
	if functions.Contains(posicoes, model.PRIMEIRA) {
		return valorPrimeiro
	} else if functions.ContainsAny(posicoes, model.SEGUNDA, model.TERCEIRA, model.QUARTA, model.QUINTA) {
		return valorOutro
	}
	return 1.0
}
