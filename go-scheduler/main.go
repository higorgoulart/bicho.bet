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
	cronJob.AddFunc("0 */12 * * *", game.GetNextGame)
	cronJob.AddFunc("0 0 * * *", payment.PaymentFee)
	cronJob.AddFunc(game.CRON, game.GameResult)
	cronJob.Start()
	select {}
}
