package ru.kode.base.internship.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class BankAccountEntity(
  val accountId: String,
  val cards: List<CardEntity>,
  val status: Status,
  val number: String,
  val accountBalance: String,
  val currency: Currency
)
