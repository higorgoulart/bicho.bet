package game

import (
	"encoding/json"
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

func stringToInt64Slice(s string) ([]int64, error) {
	var numbers []int64
	err := json.Unmarshal([]byte(s), &numbers)
	if err != nil {
		return nil, err
	}
	return numbers, nil
}

func GetIdResultado() int64 {
	var resultado model.Resultado
	database.DB.Model(&model.Resultado{}).Order("id DESC").Limit(1).Find(&resultado)
	if resultado.ID == 0 {
		return 1
	}
	return resultado.ID + 1
}

type TipoAposta string

const (
	GRUPO   TipoAposta = "GRUPO"
	DUQUE   TipoAposta = "DUQUE"
	TERNO   TipoAposta = "TERNO"
	QUADRA  TipoAposta = "QUADRA"
	QUINA   TipoAposta = "QUINA"
	DEZENA  TipoAposta = "DEZENA"
	CENTENA TipoAposta = "CENTENA"
	MILHAR  TipoAposta = "MILHAR"
)

type TipoResultado int

const (
	PRIMEIRA TipoResultado = 1
	SEGUNDA  TipoResultado = 2
	TERCEIRA TipoResultado = 3
	QUARTA   TipoResultado = 4
	QUINTA   TipoResultado = 5
	BICHO    TipoResultado = 0
)

func obterPremio(valor float64, tipo TipoAposta, apostas []int64, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := obterPosicoesCorretas(tipo, apostas, resultados, bichos)
	multiplicador := obterMultiplicador(tipo, posicoes)

	return valor * multiplicador
}

func obterPosicoesCorretas(tipo TipoAposta, apostas []int64, resultados []int64, bichos []model.Bicho) []TipoResultado {
	var posicoes []TipoResultado

	i := 0

	for _, resultado := range resultados {
		for _, apostado := range apostas {
			var acerto bool

			if tipo == DEZENA || tipo == CENTENA || tipo == MILHAR {
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
				tipoResultado := TipoResultado(index)
				posicoes = append(posicoes, tipoResultado)
			} else if tipo == DEZENA || tipo == CENTENA || tipo == MILHAR {
				bichoApostado := obterBicho(bichos, apostado)
				bichoResultado := obterBicho(bichos, resultado)

				if bichoApostado.ID == bichoResultado.ID {
					posicoes = append(posicoes, BICHO)
				}
			}
		}

		i++
	}

	return posicoes
}

func obterBicho(bichos []model.Bicho, number int64) model.Bicho {
	for _, bicho := range bichos {
		numeros, err := stringToInt64Slice(bicho.Numeros)

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

func obterMultiplicador(tipo TipoAposta, posicoes []TipoResultado) float64 {
	if len(posicoes) == 0 {
		return 0.0
	}

	switch tipo {
	case GRUPO:
		return obterMultiplicadorGrupo(posicoes)
	case DUQUE:
		return obterMultiplicadorDuque(posicoes)
	case TERNO:
		return obterMultiplicadorTerno(posicoes)
	case QUADRA:
		return obterMultiplicadorQuadra(posicoes)
	case QUINA:
		return obterMultiplicadorQuina(posicoes)
	case DEZENA:
		return obterMultiplicadorDezena(posicoes)
	case CENTENA:
		return obterMultiplicadorCentena(posicoes)
	case MILHAR:
		return obterMultiplicadorMilhar(posicoes)
	default:
		return 0.0
	}
}

func obterMultiplicadorGrupo(posicoes []TipoResultado) float64 {
	if contains(posicoes, PRIMEIRA) {
		return 12.0
	}
	return 3.0
}

func obterMultiplicadorDuque(posicoes []TipoResultado) float64 {
	if containsAll(posicoes, PRIMEIRA, SEGUNDA) {
		return 95.0
	} else if len(posicoes) == 2 {
		return 12.0
	}
	return 1.0
}

func obterMultiplicadorTerno(posicoes []TipoResultado) float64 {
	if containsAll(posicoes, PRIMEIRA, SEGUNDA, TERCEIRA) {
		return 700.0
	} else if len(posicoes) == 3 {
		return 42.0
	} else if len(posicoes) == 2 {
		return 3.0
	}
	return 0.75
}

func obterMultiplicadorQuadra(posicoes []TipoResultado) float64 {
	if containsAll(posicoes, PRIMEIRA, SEGUNDA, TERCEIRA, QUARTA) {
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

func obterMultiplicadorQuina(posicoes []TipoResultado) float64 {
	if containsAll(posicoes, PRIMEIRA, SEGUNDA, TERCEIRA, QUARTA, QUINTA) {
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

func obterMultiplicadorDezena(posicoes []TipoResultado) float64 {
	return obterMultiplicadorNumerico(posicoes, 50.0, 7.0)
}

func obterMultiplicadorCentena(posicoes []TipoResultado) float64 {
	return obterMultiplicadorNumerico(posicoes, 500.0, 60.0)
}

func obterMultiplicadorMilhar(posicoes []TipoResultado) float64 {
	return obterMultiplicadorNumerico(posicoes, 5000.0, 600.0)
}

func obterMultiplicadorNumerico(posicoes []TipoResultado, valorPrimeiro, valorOutro float64) float64 {
	if contains(posicoes, PRIMEIRA) {
		return valorPrimeiro
	} else if containsAny(posicoes, SEGUNDA, TERCEIRA, QUARTA, QUINTA) {
		return valorOutro
	}
	return 1.0
}

func contains(slice []TipoResultado, item TipoResultado) bool {
	for _, v := range slice {
		if v == item {
			return true
		}
	}
	return false
}

func containsAll(slice []TipoResultado, items ...TipoResultado) bool {
	for _, item := range items {
		if !contains(slice, item) {
			return false
		}
	}
	return true
}

func containsAny(slice []TipoResultado, items ...TipoResultado) bool {
	for _, item := range items {
		if contains(slice, item) {
			return true
		}
	}
	return false
}

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

	numeros := GenerateNumeros()
	numerosJSONB := Int64SliceToJSONB(numeros)

	newResultado := model.Resultado{
		ID:      GetIdResultado(),
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

	var apostas []model.Aposta
	if err := db.Where("jogo_id = ?", jogo.ID).Find(&apostas).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			return
		}
		functions.SetErrorLog(err, "resultado")
		return
	}

	var bichos []model.Bicho
	if err := db.Find(&bichos).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			return
		}
		functions.SetErrorLog(err, "resultado")
		return
	}

	for _, aposta := range apostas {
		numerosAposta, err := stringToInt64Slice(aposta.Numeros)

		if err != nil {
			if err == gorm.ErrRecordNotFound {
				continue
			}
			functions.SetErrorLog(err, "resultado")
			continue
		}

		premio := obterPremio(aposta.Valor, TipoAposta(aposta.Tipo), numerosAposta, numeros, bichos)
		premio = functions.RoundToTwoDecimalPlaces(premio)
		if err := db.Model(&model.Aposta{}).Where("id = ?", aposta.ID).Update("premio", premio).Error; err != nil {
			db.Rollback()
			functions.SetErrorLog(err, "resultado")
			return
		}

		var apostador model.Apostador

		if err := db.Where("id = ?", aposta.ApostadorID).First(&apostador).Error; err != nil {
			if err == gorm.ErrRecordNotFound {
				db.Rollback()
				return
			}
			db.Rollback()
			functions.SetErrorLog(err, "resultado")
			return
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

	if err := db.Commit().Error; err != nil {
		db.Rollback()
		functions.SetErrorLog(err, "resultado")
		return
	}

	functions.SetResultadoLog(newResultado, jogo)
}
