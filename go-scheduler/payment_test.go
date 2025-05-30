package main

import (
	"scheduler/model"
	"scheduler/payment"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestAplicaJuros_CalculoCorreto(t *testing.T) {
	apostador := model.Apostador{
		ID:     1,
		Divida: -100.00,
	}

	result := payment.PaymentFeeLocally(apostador)

	expectedDivida := -120.00 // -100 + 20%
	assert.Equal(t, expectedDivida, result.Divida)
}

func TestAplicaJuros_NaoAplicaQuandoDividaPositiva(t *testing.T) {
	apostador := model.Apostador{
		ID:     2,
		Divida: 50.00,
	}

	result := payment.PaymentFeeLocally(apostador)

	assert.Equal(t, 50.00, result.Divida)
}

func TestAplicaJuros_ErroDeCalculoSimulado(t *testing.T) {
	apostador := model.Apostador{
		ID:     3,
		Divida: -100.00,
	}

	result := payment.PaymentFeeLocally(apostador)

	unexpectedDivida := -130.00
	assert.NotEqual(t, unexpectedDivida, result.Divida)
}
