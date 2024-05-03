package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.CardEntity

interface CardRepository {
  val card: Flow<CardEntity>
  suspend fun fetchCardDetails(id: String)

  fun fetchBankAccountBalance()

  val bankAccountBalance: Flow<String>

  fun renameCard(cardId: CardEntity.Id,newName:String)

}
