package payment

import (
	"scheduler/database"
	"scheduler/functions"
	"scheduler/model"

	"gorm.io/gorm"
)

func PaymentFee() {
	var apostadores []model.Apostador
	taxa := 0.20
	if err := database.DB.Where("divida < ?", 0).Find(&apostadores).Error; err != nil {
		if err == gorm.ErrRecordNotFound {
			return
		}
		functions.SetErrorLog(err, "divida")
		return
	}

	tx := database.DB.Begin()
	for _, apostador := range apostadores {
		juros := apostador.Divida + (apostador.Divida * taxa)
		juros = functions.RoundToTwoDecimalPlaces(juros)
		if err := tx.Model(&model.Apostador{}).Where("id = ?", apostador.ID).Update("divida", juros).Error; err != nil {
			tx.Rollback()
			functions.SetErrorLog(err, "divida")
			return
		}
	}

	if err := tx.Commit().Error; err != nil {
		tx.Rollback()
		functions.SetErrorLog(err, "divida")
		return
	}

	database.DB.Where("divida < ?", 0).Find(&apostadores)
	functions.SetDividaLog(apostadores)
}
