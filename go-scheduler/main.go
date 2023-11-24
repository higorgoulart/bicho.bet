package main

import (
	"scheduler/database"
	"scheduler/game"
	"scheduler/payment"

	"github.com/robfig/cron"
)

func main() {
	database.Connect()
	cronJob := cron.New()

	cronJob.AddFunc("* * * * *", payment.PaymentFee)
	cronJob.AddFunc("* * * * *", game.GameResult)
	cronJob.Start()
	select {}
}
