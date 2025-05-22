package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type DuqueStrategy struct{}

func (s DuqueStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.DUQUE, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorDuque(posicoes)
	return valor * multiplicador
}

func obterMultiplicadorDuque(posicoes []model.TipoResultado) float64 {
	if functions.ContainsAll(posicoes, model.PRIMEIRA, model.SEGUNDA) {
		return 95.0
	} else if len(posicoes) == 2 {
		return 12.0
	}
	return 1.0
}
