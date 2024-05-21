package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.CardDetailsEntity

interface CardRepository {
  val cardFlow: Flow<CardDetailsEntity>
  suspend fun fetchCardDetails()

  suspend fun getCardDetails(id:String)
  suspend fun fetchBankAccountBalance()

  suspend fun renameCard(id: CardDetailsEntity.Id, newName:String)

  val balance: Flow<Balance>

  suspend fun findRelatedCardsIds(id:String)

  val relatedCardsIdsList: Flow<List<String>>
}
