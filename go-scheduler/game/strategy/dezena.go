package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type DezenaStrategy struct{}

func (s DezenaStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.DEZENA, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorDezena(posicoes)
	return valor * multiplicador
}

func obterMultiplicadorDezena(posicoes []model.TipoResultado) float64 {
	return functions.ObterMultiplicadorNumerico(posicoes, 50.0, 7.0)
}
