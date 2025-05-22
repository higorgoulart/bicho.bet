package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type MilharStrategy struct{}

func (s MilharStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.MILHAR, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorMilhar(posicoes)
	return valor * multiplicador
}

func obterMultiplicadorMilhar(posicoes []model.TipoResultado) float64 {
	return functions.ObterMultiplicadorNumerico(posicoes, 5000.0, 600.0)
}
