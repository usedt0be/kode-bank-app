package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.data.mapper.toCardEntity
import ru.kode.base.internship.data.storage.dataSource.BankAccountDataSource
import ru.kode.base.internship.data.storage.dataSource.CardDataSource
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardsRepositoryImpl @Inject constructor(
  private val cardDataSource: CardDataSource,
  private val bankAccountDataSource: BankAccountDataSource,
) : CardRepository {

  override val cardFlow = MutableStateFlow<CardEntity>(Mocks.defaultCardEntity)

  override suspend fun fetchCardDetails(id: String) {
    val card = cardDataSource.getCardsById(id.toLong()).toCardEntity()
    cardFlow.update { card }
  }

  override val bankAccountBalance = MutableStateFlow<String>("")


  override fun renameCard(cardId: CardEntity.Id, newName: String) {
    val renamedCard = Mocks.renameCard(cardId,newName)
    cardFlow.update { renamedCard }
  }

  override suspend fun fetchBankAccountBalance() {
     bankAccountDataSource.getAccounts().collect { bankAccountModelList ->
       bankAccountBalance.update {
         bankAccountModelList.find { bankAccountModel ->
           bankAccountModel.id.toString() == cardFlow.value.accountId
         }?.balance.toString()
       }
    }
  }
}
