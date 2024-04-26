package ru.kode.base.internship.domain.entity

data class DepositsEntity(
  val depositId: String,
  val description: String,
  val currencyType: Currency,
  val balance: String,
  val rate: String,
  val cardExpiryDate: String
)
