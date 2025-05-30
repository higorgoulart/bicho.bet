package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type MilharStrategy struct{}

func (s MilharStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := obterPosicoesCorretasMilhar(apostas, resultados, bichos)
	multiplicador := obterMultiplicadorMilhar(posicoes)
	return valor * multiplicador
}

func obterPosicoesCorretasMilhar(apostas []int64, resultados []int64, bichos []model.Bicho) []model.TipoResultado {
	var posicoes []model.TipoResultado

	i := 0

	for _, resultado := range resultados {
		for _, apostado := range apostas {
			acerto := resultado == apostado

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

func obterMultiplicadorMilhar(posicoes []model.TipoResultado) float64 {
	return functions.ObterMultiplicadorNumerico(posicoes, 5000.0, 600.0)
}
