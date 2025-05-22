package strategy

import "scheduler/model"

type PremioStrategy interface {
	CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64
}

func getStrategy(tipo model.TipoAposta) (strategy.PremioStrategy, error) {
	switch tipo {
	case model.GRUPO:
		return strategy.GrupoStrategy{}, nil
	case model.DUQUE:
		return strategy.DuqueStrategy{}, nil
	case model.TERNO:
		return strategy.TernoStrategy{}, nil
	case model.QUADRA:
		return strategy.QuadraStrategy{}, nil
	case model.QUINA:
		return strategy.QuinaStrategy{}, nil
	case model.DEZENA:
		return strategy.DezenaStrategy{}, nil
	case model.CENTENA:
		return strategy.CentenaStrategy{}, nil
	case model.MILHAR:
		return strategy.MilharStrategy{}, nil
	default:
		return nil, errors.New("tipo de aposta não suportado")
	}
}
