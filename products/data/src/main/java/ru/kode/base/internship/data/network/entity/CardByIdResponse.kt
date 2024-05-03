package ru.kode.base.internship.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class CardByIdResponse(
  val id: Int,
  val accountId: Int,
  val name: String,
  val number: String,
  val status: String,
  val expiredAt: String,
  val paymentSystem: String,
)
