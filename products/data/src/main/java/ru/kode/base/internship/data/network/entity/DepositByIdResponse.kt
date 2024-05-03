package ru.kode.base.internship.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class DepositByIdResponse(
  val name: String,
  val rate: Double,
  val status: String,
  val balance: Int,
  val currency: String,
  val closeDate: String,
)
