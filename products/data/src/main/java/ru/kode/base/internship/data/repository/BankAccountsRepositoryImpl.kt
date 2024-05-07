package ru.kode.base.internship.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.mapper.toBankAccountEntity
import ru.kode.base.internship.data.mapper.toBankAccountModel
import ru.kode.base.internship.data.mapper.toCardEntity
import ru.kode.base.internship.data.mapper.toCardModel
import ru.kode.base.internship.data.network.ProductApi
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.domain.repository.BankAccountRepository
import ru.kode.base.internship.products.data.ProductsDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class BankAccountsRepositoryImpl @Inject constructor(
  private val db: ProductsDB,
  private val api: ProductApi,
) : BankAccountRepository {

  private val bankAccountQueries = db.bankAccountModelQueries
  private val cardQueries = db.cardModelQueries
  override suspend fun fetchBankAccount() {
    val bankAccountsResponse = api.getBankAccounts()
    val bankAccountEntityList = bankAccountsResponse.accounts.map { it.toBankAccountEntity() }

    val bankAccountsSM = bankAccountEntityList.map { it.toBankAccountModel() }
    bankAccountQueries.transaction {
      bankAccountsSM.forEach { bankAccount ->
        bankAccountQueries.insertBankAccountObject(bankAccount)
      }
    }

    cardQueries.transaction {
      bankAccountEntityList.forEach { bankAccountEntity ->
        bankAccountEntity.cards.forEach { cardEntity ->
          cardQueries.insertCardModelObject(cardEntity.toCardModel())
        }
      }
    }
  }

  override fun getBankAccounts(): Flow<List<BankAccountEntity>> {
    val bankAccountsFlow =
      bankAccountQueries.getAllBankAccounts().asFlow().mapToList(Dispatchers.IO).map { bankAccounts ->
        bankAccounts.map { bankAccountModel ->
          bankAccountModel.toBankAccountEntity()
        }
      }.distinctUntilChanged()

    val cardsFlow = cardQueries.getAllCards().asFlow().mapToList(Dispatchers.IO).map { cardModels ->
      cardModels.map { cardModel ->
        cardModel.toCardEntity()
      }
    }.distinctUntilChanged()

    val bankEntityList = mutableListOf<BankAccountEntity>()
      return bankAccountsFlow.combine(cardsFlow) { bankAccounts, cards ->
      bankAccounts.forEach { bankAccount ->
        val relatedCards = cards.filter { it.accountId == bankAccount.accountId }

        val bankAccountEntity = BankAccountEntity(
          accountId = bankAccount.accountId,
          cards = relatedCards,
          status = Status.valueOf(bankAccount.status.toString()),
          number = bankAccount.number,
          accountBalance = bankAccount.accountBalance,
          currency = Currency.valueOf(bankAccount.currency.toString())
        )
        bankEntityList.add(bankAccountEntity)
      }
      bankEntityList
    }
  }
}


