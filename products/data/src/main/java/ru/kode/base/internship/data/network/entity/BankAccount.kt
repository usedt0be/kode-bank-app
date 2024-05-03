package ru.kode.base.internship.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class BankAccount(
  val accountId: Int,
  val number: String,
  val status: String,
  val balance: Int,
  val currency: String,
  val cards: List<CardResponse>,
)
