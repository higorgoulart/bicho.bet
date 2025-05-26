package strategy

import (
	"errors"
	"scheduler/model"
)

type PremioStrategy interface {
	CalcularPremio(valor float64, apostas, resultados []int64, bichos []model.Bicho) float64
}

func GetStrategy(tipo model.TipoAposta) (PremioStrategy, error) {
	switch tipo {
	case model.GRUPO:
		return GrupoStrategy{}, nil
	case model.DUQUE:
		return DuqueStrategy{}, nil
	case model.TERNO:
		return TernoStrategy{}, nil
	case model.QUADRA:
		return QuadraStrategy{}, nil
	case model.QUINA:
		return QuinaStrategy{}, nil
	case model.DEZENA:
		return DezenaStrategy{}, nil
	case model.CENTENA:
		return CentenaStrategy{}, nil
	case model.MILHAR:
		return MilharStrategy{}, nil
	default:
		return nil, errors.New("tipo de aposta não suportado")
	}
}
