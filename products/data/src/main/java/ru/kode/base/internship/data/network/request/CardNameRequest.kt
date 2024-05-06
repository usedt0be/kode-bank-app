package ru.kode.base.internship.data.network.request

import kotlinx.serialization.Serializable

@Serializable
data class CardNameRequest(
  val newName: String,
)
