package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.CardEntity

interface CardRepository {
  val cardFlow: Flow<CardEntity>
  suspend fun fetchCardDetails(id: String)


  suspend fun fetchBankAccountBalance()

  val bankAccountBalance: Flow<String>

  fun renameCard(cardId: CardEntity.Id,newName:String)

}
