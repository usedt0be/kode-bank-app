package ru.kode.base.internship.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class CardDetailsEntity(
  val cardId: Id,
  val accountId: String,
  var name: String,
  val number: String,
  val expiredAt: String,
  val paymentSystem: PaymentSystem,
  val status: Status,
  val type: String,
) {
  @JvmInline
  value class Id(val value: String)
}
