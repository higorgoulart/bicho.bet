// Code generated by gorm.io/gen. DO NOT EDIT.
// Code generated by gorm.io/gen. DO NOT EDIT.
// Code generated by gorm.io/gen. DO NOT EDIT.

package model

const TableNameApostador = "apostador"

// Apostador mapped from table <apostador>
type Apostador struct {
	ID         int64   `gorm:"column:id;primaryKey" json:"id"`
	Depositado float64 `gorm:"column:depositado" json:"depositado"`
	Nome       string  `gorm:"column:nome" json:"nome"`
	Saldo      float64 `gorm:"column:saldo" json:"saldo"`
	Telefone   string  `gorm:"column:telefone" json:"telefone"`
	Cpf        string  `gorm:"column:cpf" json:"cpf"`
	Divida     float64 `gorm:"column:divida" json:"divida"`
	Limite     float64 `gorm:"column:limite" json:"limite"`
}

// TableName Apostador's table name
func (*Apostador) TableName() string {
	return TableNameApostador
}