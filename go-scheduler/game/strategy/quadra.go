package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type QuadraStrategy struct{}

func (s QuadraStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.QUADRA, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorQuadra(posicoes)
	return valor * multiplicador
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