package ru.kode.base.internship.data.repository

import android.util.Log
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.mapper.bankAccountMapper
import ru.kode.base.internship.data.mapper.cardEntityMapper
import ru.kode.base.internship.data.mapper.toBankAccountModel
import ru.kode.base.internship.data.mapper.toCardEntity
import ru.kode.base.internship.data.mapper.toCardModel
import ru.kode.base.internship.data.network.ProductApi
import ru.kode.base.internship.data.storage.dataSource.BankAccountDataSource
import ru.kode.base.internship.data.storage.dataSource.CardDataSource
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.domain.repository.BankAccountRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class BankAccountsRepositoryImpl @Inject constructor(
  private val api: ProductApi,
  private val bankAccountDataSource: BankAccountDataSource,
  private val cardDataSource: CardDataSource,
) : BankAccountRepository {


  private var bankAccounts = MutableStateFlow<List<BankAccountEntity>>(emptyList())

  override val bankAccountFlow: Flow<List<BankAccountEntity>>
    get() = bankAccounts


  override suspend fun fetchBankAccount() {
    val bankAccountsResponse = api.getBankAccounts()
    val bankAccountList = mutableListOf<BankAccountEntity>()
    val cardsList = mutableListOf<CardEntity>()

    bankAccountsResponse.accounts.forEach { bankAccount ->
      bankAccount.cards.forEach { card ->
        val cardById = api.getCardById(card.card_id.toInt())

        bankAccountList.add(bankAccountMapper(bankAccount, cardById))
        cardsList.add(cardEntityMapper(card, cardById))
      }
    }


    val bankAccountsSM = bankAccountList.map { it.toBankAccountModel() }

    bankAccountDataSource.queries.transaction {
      bankAccountsSM.forEach { bankAccount ->
        bankAccountDataSource.queries.insertBankAccountObject(bankAccount)
      }
    }

    val cardsSM = cardsList.map { it.toCardModel() }

    cardDataSource.queries.transaction {
      cardsSM.forEach { cardModel ->
        cardDataSource.queries.insertCardObject(cardModel)
      }
    }
  }

  override fun getBankAccountsFromDb():Flow<List<BankAccountEntity>> {
    val bankAccountsDb = bankAccountDataSource.getAccounts().distinctUntilChanged()
    val cardsDb = cardDataSource.getAllCards().distinctUntilChanged()
    val bankAccountList = mutableListOf<BankAccountEntity>()

    return bankAccountsDb.combine(cardsDb) { bankAccounts, cards ->
      bankAccounts.forEach { bankAccount ->
        val relatedCards = cards.filter { it.accountId == bankAccount.id }.map { it.toCardEntity() }

        val bankAccountEntity = BankAccountEntity(
          accountId = bankAccount.id.toString(),
          cards = relatedCards,
          status = Status.valueOf(bankAccount.status),
          number = bankAccount.number,
          accountBalance = bankAccount.balance,
          currency = Currency.valueOf(bankAccount.currency)
        )
        bankAccountList.add(bankAccountEntity)
      }
      bankAccountList
    }
  }
}


