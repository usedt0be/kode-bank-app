package ru.kode.base.internship.ui.entity

import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status

data class CardEntityUi(
  val balance: Balance,
//  val currency: Currency,
  val cardId: String,
  val accountId: String,
  var name: String,
  val number: String,
  val expiredAt: String,
  val paymentSystem: PaymentSystem,
  val status: Status,
  val type: String,
)
