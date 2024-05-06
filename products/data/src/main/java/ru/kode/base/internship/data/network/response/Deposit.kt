package ru.kode.base.internship.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Deposit(
  val depositId: Int,
  val balance: Int,
  val currency: String,
  val status: String,
  val name: String,
)
