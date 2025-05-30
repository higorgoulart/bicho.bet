package strategy

import (
    "testing"

    "scheduler/model"
    "github.com/stretchr/testify/assert"
)

// Mock for functions.Contains
func containsPrimeira(posicoes []model.TipoResultado, value model.TipoResultado) bool {
    for _, v := range posicoes {
        if v == value {
            return true
        }
    }
    return false
}

func TestObterMultiplicadorGrupo_Primeira(t *testing.T) {
    posicoes := []model.TipoResultado{model.PRIMEIRA, model.SEGUNDA}
    result := obterMultiplicadorGrupo(posicoes)
    assert.Equal(t, 12.0, result)
}

func TestObterMultiplicadorGrupo_NotPrimeira(t *testing.T) {
    posicoes := []model.TipoResultado{model.SEGUNDA, model.TERCEIRA}
    result := obterMultiplicadorGrupo(posicoes)
    assert.Equal(t, 3.0, result)
}

func TestObterMultiplicadorGrupo_Empty(t *testing.T) {
    posicoes := []model.TipoResultado{}
    result := obterMultiplicadorGrupo(posicoes)
    assert.Equal(t, 3.0, result)
}

func TestCalcularPremio_Primeira(t *testing.T) {
    // Setup
    strategy := GrupoStrategy{}
    valor := 10.0
    apostas := []int64{1, 2}
    resultados := []int64{3, 4}
    bichos := []model.Bicho{}

    // Mock functions.ObterPosicoesCorretasPorBicho to return PRIMEIRA
    oldFunc := functions.ObterPosicoesCorretasPorBicho
    functions.ObterPosicoesCorretasPorBicho = func(a, r []int64, b []model.Bicho) []model.TipoResultado {
        return []model.TipoResultado{model.PRIMEIRA}
    }
    defer func() { functions.ObterPosicoesCorretasPorBicho = oldFunc }()

    result := strategy.CalcularPremio(valor, apostas, resultados, bichos)
    assert.Equal(t, 120.0, result)
}

func TestCalcularPremio_NotPrimeira(t *testing.T) {
    // Setup
    strategy := GrupoStrategy{}
    valor := 10.0
    apostas := []int64{1, 2}
    resultados := []int64{3, 4}
    bichos := []model.Bicho{}

    // Mock functions.ObterPosicoesCorretasPorBicho to return SEGUNDA
    oldFunc := functions.ObterPosicoesCorretasPorBicho
    functions.ObterPosicoesCorretasPorBicho = func(a, r []int64, b []model.Bicho) []model.TipoResultado {
        return []model.TipoResultado{model.SEGUNDA}
    }
    defer func() { functions.ObterPosicoesCorretasPorBicho = oldFunc }()

    result := strategy.CalcularPremio(valor, apostas, resultados, bichos)
    assert.Equal(t, 30.0, result)
}

func TestCalcularPremio_EmptyPosicoes(t *testing.T) {
    // Setup
    strategy := GrupoStrategy{}
    valor := 10.0
    apostas := []int64{}
    resultados := []int64{}
    bichos := []model.Bicho{}

    // Mock functions.ObterPosicoesCorretasPorBicho to return empty
    oldFunc := functions.ObterPosicoesCorretasPorBicho
    functions.ObterPosicoesCorretasPorBicho = func(a, r []int64, b []model.Bicho) []model.TipoResultado {
        return []model.TipoResultado{}
    }
    defer func() { functions.ObterPosicoesCorretasPorBicho = oldFunc }()

    result := strategy.CalcularPremio(valor, apostas, resultados, bichos)
    assert.Equal(t, 30.0, result)
}