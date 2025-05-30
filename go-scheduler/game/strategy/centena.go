package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type CentenaStrategy struct{}

func (s CentenaStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := obterPosicoesCorretasCentena(apostas, resultados, bichos)
	multiplicador := obterMultiplicadorCentena(posicoes)
	return valor * multiplicador
}

func obterPosicoesCorretasCentena(apostas []int64, resultados []int64, bichos []model.Bicho) []model.TipoResultado {
	var posicoes []model.TipoResultado

	i := 0

	for _, resultado := range resultados {
		for _, apostado := range apostas {
			acerto := functions.FormatNumber(resultado, 3) == functions.FormatNumber(apostado, 3)

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

func obterMultiplicadorCentena(posicoes []model.TipoResultado) float64 {
	return functions.ObterMultiplicadorNumerico(posicoes, 500.0, 60.0)
}
