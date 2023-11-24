package functions

import (
	"fmt"
	"log"
	"math"
	"os"
	"scheduler/model"
	"strings"
	"time"
)

func SetErrorLog(errLog error, operacao string) {
	var file *os.File
	var err error
	var logMessage string
	errObj := errLog.Error()

	if operacao == "resultado" {
		file, err = os.OpenFile("resultados_error.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0777)
		logMessage = fmt.Sprintf("%s - Erro ao gerar resultados | %v\n", time.Now().Format("2006-01-02T15:04:05"), errObj)
	} else {
		file, err = os.OpenFile("dividas_error.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0777)
		logMessage = fmt.Sprintf("%s - Erro ao aplicar juros | %v\n", time.Now().Format("2006-01-02T15:04:05"), errObj)
	}

	if err != nil {
		log.Panic(err)
	}
	defer file.Close()

	_, writeErr := file.WriteString(logMessage)

	if writeErr != nil {
		log.Panic(writeErr)
	}
}

func SetDividaLog(apostadores []model.Apostador) {
	file, err := os.OpenFile("dividas_log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0777)
	if err != nil {
		log.Panic(err)
	}
	defer file.Close()

	for _, apostador := range apostadores {
		logMessage := fmt.Sprintf("%s - Juros aplicado | Conta: %d | Divida atual: %.2f\n", time.Now().Format("2006-01-02T15:04:05"), apostador.ID, apostador.Divida)
		file.WriteString(logMessage)
	}
}

func SetResultadoLog(resultado model.Resultado, jogo model.Jogo) {
	file, err := os.OpenFile("resultados_log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0777)
	if err != nil {
		log.Panic(err)
	}
	defer file.Close()

	numerosFormatados := fmt.Sprintf("%v", resultado.Numeros)
	numerosFormatados = strings.Trim(numerosFormatados, "[]")
	numerosFormatados = "[" + numerosFormatados + "]"

	logMessage := fmt.Sprintf("%s - Resultado gerado | Jogo: %d | Numeros: %s\n", time.Now().Format("2006-01-02T15:04:05"), jogo.ID, numerosFormatados)
	file.WriteString(logMessage)
}

func RoundToTwoDecimalPlaces(value float64) float64 {
	return math.Round(value*100) / 100
}

func FormatNumber(number int64, format int) int64 {
	if format == 2 {
		return number % 100
	}

	return number % 1000
}
