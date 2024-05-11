package ru.kode.base.internship.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class BankAccountResponse (
  val accounts: List<BankAccount>,
)


