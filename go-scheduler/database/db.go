package database

import (
	"database/sql"
	"log"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"gorm.io/gorm/schema"
)

var (
	DB  *gorm.DB
	err error
)

func Connect() {
	dsn := "host=localhost user=postgres password=123456 dbname=bichobet port=5432 sslmode=disable"

	sqlDB, _ := sql.Open("pgx", dsn)
	DB, err = gorm.Open(postgres.New(postgres.Config{Conn: sqlDB}), &gorm.Config{
		NamingStrategy: schema.NamingStrategy{
			SingularTable: false}})
	if err != nil {
		log.Panic(err)
	}
}
