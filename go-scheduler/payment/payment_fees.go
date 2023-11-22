package payment

import "fmt"

func Payment() {
	fee := 0.20
	fmt.Println(fee)
	// tx, err := database.DB.Begin()
	// functions.CheckError(err)
	// defer tx.Rollback()

	// rows, err := database.DB.Query("SELECT id, divida FROM apostador WHERE divida < 0")
	// functions.CheckError(err)
	// defer rows.Close()

	// var paymentArray []models.PaymentInfo
	// for rows.Next() {
	// 	var apo models.Apostador
	// 	if err := rows.Scan(&apo.Id, &apo.Divida); err != nil {
	// 		log.Panic(err)
	// 	}

	// 	newDivida := apo.Divida.Float64 + (apo.Divida.Float64 * fee)
	// 	newDividaStr := strconv.FormatFloat(newDivida, 'f', 2, 64)

	// 	cmd := "UPDATE apostador SET divida = $1 WHERE id = $2"

	// 	_, err := tx.Exec(cmd, newDividaStr, apo.Id.Int64)
	// 	functions.CheckError(err)

	// 	paymentArray = append(paymentArray, models.PaymentInfo{ID: apo.Id.Int64,
	// 		Divida:           apo.Divida.Float64,
	// 		DividaAtualizada: newDivida})
	// }

	// if err := tx.Commit(); err != nil {
	// 	functions.CheckError(err)
	// }
	// functions.SetPaymentFeeLog(paymentArray)
}
