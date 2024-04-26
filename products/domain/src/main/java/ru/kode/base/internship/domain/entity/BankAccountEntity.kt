package ru.kode.base.internship.domain.entity

data class BankAccountEntity(
  val description: String,
  val accountId: String,
  val cards: List<CardEntity>,
  val status: Status,
  val number: String,
  val accountBalance: String,
  val currency: Currency
)
