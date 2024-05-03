package ru.kode.base.internship.data.storage.dataSource

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.CardModel
import ru.kode.base.internship.products.data.CardModelQueries

interface CardDataSource {

  val queries: CardModelQueries
  fun getAllCards(): Flow<List<CardModel>>

  suspend fun getCardsById(id: Long): CardModel

  suspend fun insertCard(
    id: Long,
    accountId: Long,
    name:String,
    number: String,
    expireAt:String,
    paymentSystem: String,
    status: String,
    type: String,
  )
  suspend fun insertCardObject(card : CardModel)
}