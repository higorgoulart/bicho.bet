package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type CentenaStrategy struct{}

func (s CentenaStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretas(model.CENTENA, apostas, resultados, bichos)
	multiplicador := obterMultiplicadorCentena(posicoes)
	return valor * multiplicador
}

func obterMultiplicadorCentena(posicoes []model.TipoResultado) float64 {
	return functions.ObterMultiplicadorNumerico(posicoes, 500.0, 60.0)
}
