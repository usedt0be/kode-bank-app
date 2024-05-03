package ru.kode.base.internship.data.network.entity

import kotlinx.serialization.Serializable
import ru.kode.base.internship.domain.entity.PaymentSystem


@Serializable
data class CardResponse(
  val name: String,
  val number: String,
  val status: String,
  val card_id: String,
  val card_type: String,
  val payment_system: String
)