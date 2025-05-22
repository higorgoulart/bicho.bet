package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type TernoStrategy struct{}

func (s TernoStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.TERNO, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorTerno(posicoes)
	return valor * multiplicador
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
