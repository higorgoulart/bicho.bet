package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type QuinaStrategy struct{}

func (s QuinaStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.QUINA, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorQuina(posicoes)
	return valor * multiplicador
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
