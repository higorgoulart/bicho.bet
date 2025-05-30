package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type DezenaStrategy struct{}

func (s DezenaStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := obterPosicoesCorretasDezena(apostas, resultados, bichos)
	multiplicador := obterMultiplicadorDezena(posicoes)
	return valor * multiplicador
}

func obterPosicoesCorretasDezena(apostas []int64, resultados []int64, bichos []model.Bicho) []model.TipoResultado {
	var posicoes []model.TipoResultado

	i := 0

	for _, resultado := range resultados {
		for _, apostado := range apostas {
			acerto := functions.FormatNumber(resultado, 2) == functions.FormatNumber(apostado, 2)

			if acerto {
				index := i + 1
				tipoResultado := model.TipoResultado(index)
				posicoes = append(posicoes, tipoResultado)
			} else {
				bichoApostado := functions.ObterBicho(bichos, apostado)
				bichoResultado := functions.ObterBicho(bichos, resultado)

				if bichoApostado.ID == bichoResultado.ID {
					posicoes = append(posicoes, model.BICHO)
				}
			}
		}

		i++
	}

	return posicoes
}

func obterMultiplicadorDezena(posicoes []model.TipoResultado) float64 {
	return functions.ObterMultiplicadorNumerico(posicoes, 50.0, 7.0)
}
