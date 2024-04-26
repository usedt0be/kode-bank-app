package ru.kode.base.internship.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.core.domain.BaseUseCase
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.DepositsEntity
import ru.kode.base.internship.domain.repository.BankAccountRepository
import ru.kode.base.internship.domain.repository.CardRepository
import ru.kode.base.internship.domain.repository.DepositRepository
import javax.inject.Inject
import kotlin.random.Random

@SingleIn(AppScope::class)
class ProductsUseCase @Inject constructor(
  private val bankAccountRepository: BankAccountRepository,
  private val cardRepository: CardRepository,
  private val depositsRepository: DepositRepository,
) : BaseUseCase<ProductsUseCase.State>(State()) {
  data class State(
    val bankAccountsState: LceState = LceState.None,
    val depositsState: LceState = LceState.None,
    val cardsState: LceState = LceState.None,
    val listAcc: List<BankAccountEntity> = emptyList()
  )

  suspend fun fetchBankAccounts(): Flow<List<BankAccountEntity>> {
    setState { copy(bankAccountsState = LceState.Loading) }
    delay(2000)
    return try {
      bankAccountRepository.updateBankAccountMocks()
      val bankAccountFlow = bankAccountRepository.bankAccount

      if (Random.nextBoolean()) {
        setState { copy(bankAccountsState = LceState.Content) }
      } else {
        setState { copy(bankAccountsState = LceState.Error("Failed to load accounts")) }
      }
      bankAccountFlow
    } catch (e: Exception) {
      setState { copy(bankAccountsState = LceState.Error("Failed to load accounts")) }
      throw e
    }
  }

  val bankAccounts = bankAccountRepository.bankAccount

  val bankAccountsState: Flow<LceState> =
    stateFlow.map { it.bankAccountsState }.distinctUntilChanged()

  suspend fun fetchDeposits(): Flow<List<DepositsEntity>> {
    setState { copy(depositsState = LceState.Loading) }
    delay(2000)

    return try {
      depositsRepository.updateDepositMocks()
      val deposits = depositsRepository.depositsFlow

      if (Random.nextBoolean()) {
        setState { copy(depositsState = LceState.Content) }
      } else {
        setState { copy(depositsState = LceState.Error("Failed to load deposits")) }
      }

      deposits
    } catch (e: Exception) {
      setState { copy(depositsState = LceState.Error("Failed to load deposits")) }
      throw e
    }
  }

  val deposits = depositsRepository.depositsFlow

  val depositsState: Flow<LceState> = stateFlow.map {
    it.depositsState
  }.distinctUntilChanged()

  suspend fun fetchCardDetails(cardId: String): Flow<CardEntity> {
    setState { copy(bankAccountsState = LceState.Loading) }
    return try {
      val cardDetails = cardRepository.fetchCards(cardId)
      setState { copy(bankAccountsState = LceState.Content) }
      cardDetails
    } catch (e: Exception) {
      setState { copy(bankAccountsState = LceState.Error(e.message)) }
      throw e
    }
  }

  val cardState: Flow<LceState> = stateFlow.map {
    it.cardsState
  }.distinctUntilChanged()

  suspend fun fetchDepositRates(depositId: String): Flow<String> {
    setState { copy(bankAccountsState = LceState.Loading) }
    return try {
      setState { copy(bankAccountsState = LceState.Content) }
      depositsRepository.getDepositRate(id = depositId)
    } catch (e: Exception) {
      setState { copy(bankAccountsState = LceState.Error(e.message)) }
      throw e
    }
  }
}
