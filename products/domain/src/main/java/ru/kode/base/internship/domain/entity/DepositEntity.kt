package ru.kode.base.internship.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DepositEntity(
  val depositId: String,
  val name: String,
  val currencyType: Currency,
  val status: Status,
  val balance: String,
  val rate: String,
  val closeDate: String
)
