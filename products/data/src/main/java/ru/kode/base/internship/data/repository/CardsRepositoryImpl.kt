package ru.kode.base.internship.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.data.mapper.cardEntityMapper
import ru.kode.base.internship.data.mapper.cardModelToEntityMapper
import ru.kode.base.internship.data.mapper.toCardDetailsModel
import ru.kode.base.internship.data.network.ProductApi
import ru.kode.base.internship.data.network.request.CardNameRequest
import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.repository.CardRepository
import ru.kode.base.internship.products.data.ProductsDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardsRepositoryImpl @Inject constructor(
  private val db: ProductsDB,
  private val api: ProductApi,
) : CardRepository {

  override val cardFlow = MutableStateFlow(Mocks.defaultCardEntity)

  private val cardQueries = db.cardModelQueries
  private val bankQueries = db.bankAccountModelQueries
  private val cardDetailsQueries = db.cardDetailsModelQueries



  override suspend fun fetchCardDetails() {
    val bankAccountsResponse = api.getBankAccounts()
    val cardsList = mutableListOf<CardDetailsEntity>()

    bankAccountsResponse.accounts.forEach { bankAccount ->
      bankAccount.cards.forEach { card ->
        val cardById = api.getCardById(card.card_id.toInt())

        cardsList.add(cardEntityMapper(card, cardById))
      }
    }

    val cardsSM = cardsList.map { it.toCardDetailsModel() }

    cardDetailsQueries.transaction {
      cardsSM.forEach { cardModel ->
        cardDetailsQueries.insertCardObject(cardModel)
      }
    }
  }

  override suspend fun getCardDetails(id: String) {
    val cardDetails = cardDetailsQueries.getCardById(id.toLong()).executeAsOne()
    val card = cardQueries.getCardsById(id.toLong()).executeAsOne()
    val cardDetailsEntity = cardModelToEntityMapper(card, cardDetails)
    cardFlow.update { cardDetailsEntity }
  }

  override suspend fun renameCard(id: String, newName: String) {
    api.renameCard(id, CardNameRequest(newName = newName))
  }

  val _balance = MutableStateFlow(Balance("41",Currency.RUB))
  override val balance: Flow<Balance>
    get() = _balance

  override suspend fun fetchBankAccountBalance() {
    val bankAccountModelsList = bankQueries.getAllBankAccounts().asFlow().mapToList(Dispatchers.IO)
    bankAccountModelsList.collect { bankAccountModelList ->
       val balance = bankAccountModelList.find { bankAccountModel ->
         bankAccountModel.id.toString() == cardFlow.value.accountId
      }?.balance
      val currency = bankAccountModelList.find { bankAccountModel ->
        bankAccountModel.id.toString() == cardFlow.value.accountId
      }?.currency

      if (balance != null && currency !=null) {
        _balance.value = Balance(balance, Currency.valueOf(currency))
      }
    }
  }
}
