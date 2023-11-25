package game

import (
	"scheduler/database"
	"scheduler/model"
	"strings"
	"time"
)

var (
	CRON string
)

func GetNextGame() {
	var jogo model.Jogo

	database.DB.Where("dt_fim BETWEEN ? AND ? AND status = ?",
		time.Now().Local(),
		time.Now().Add(time.Hour*time.Duration(12)),
		"ABERTO").Find(&jogo)

	nextGame := jogo.DtFim.Format("01-02 15:04")
	cronFormat := "* * * * *"
	cronFormatSlice := strings.Split(cronFormat, " ")
	cronFormatSlice[0] = nextGame[9:11]
	cronFormatSlice[1] = nextGame[6:8]
	cronFormatSlice[2] = nextGame[3:5]
	cronFormatSlice[3] = nextGame[0:2]

	CRON = strings.Join(cronFormatSlice, " ")
}
