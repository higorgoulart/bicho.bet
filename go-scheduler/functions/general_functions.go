package functions

import (
	"fmt"
	"log"
	"os"
	"time"
)

func CheckError(err error) {
	if err != nil {
		SetErrorLog(err)
	}
}

func SetErrorLog(errLog error) {
	file, err := os.OpenFile("payment_fee_log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0777)
	if err != nil {
		log.Panic(err)
	}
	defer file.Close()

	errObj := errLog.Error()

	logMessage := fmt.Sprintf("%s - Erro ao realizar processo de aplicar juros | %v\n", time.Now().Format("2006-01-02T15:04:05"), errObj)
	file.WriteString(logMessage)
}

// func SetPaymentFeeLog(paymentArray []model.PaymentInfo) {
// 	file, err := os.OpenFile("payment_fee_log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0777)
// 	if err != nil {
// 		log.Panic(err)
// 	}
// 	defer file.Close()

// 	for _, payment := range paymentArray {
// 		logMessage := fmt.Sprintf("%s - Juros aplicado | Conta: %d | Divida anterior: %.2f | Divida atual: %.2f\n", time.Now().Format("2006-01-02T15:04:05"), payment.ID, payment.Divida, payment.DividaAtualizada)
// 		file.WriteString(logMessage)
// 	}
// }
