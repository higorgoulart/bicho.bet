package strategy

import (
	"scheduler/functions"
	"scheduler/model"
)

type GrupoStrategy struct{}

func (s GrupoStrategy) CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64 {
	posicoes := functions.ObterPosicoesCorretasPorBicho(apostas, resultados, bichos)
	multiplicador := obterMultiplicadorGrupo(posicoes)
	return valor * multiplicador
}

func obterMultiplicadorGrupo(posicoes []model.TipoResultado) float64 {
	if functions.Contains(posicoes, model.PRIMEIRA) {
		return 12.0
	}
	return 3.0
}
