package main

import (
	"scheduler/database"
	"scheduler/game"

	"github.com/robfig/cron"
)

func main() {
	database.Connect()
	cronJob := cron.New()
	// cronJob.AddFunc("* * * * *", payment.Payment)
	cronJob.AddFunc("* * * * *", game.Game)
	cronJob.Start()
	select {}
}
