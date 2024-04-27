package ru.kode.base.internship.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class CardEntity(
  val cardId: Id,
  val accountId: String,
  val name: String,
  val number: String,
  val expireAt: String,
  val paymentSystem: PaymentSystem,
  val status: Status,
  val type: String,
) {
  @JvmInline
  value class Id(val value: String)
}
