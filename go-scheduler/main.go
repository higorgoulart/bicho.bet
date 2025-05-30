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
	gameService := game.NewGameService(database.DB)

	cronJob.AddFunc("0 0 * * *", payment.PaymentFee)
	cronJob.AddFunc("* * * * *", gameService.GameResult)
	cronJob.Start()
	select {}
}
