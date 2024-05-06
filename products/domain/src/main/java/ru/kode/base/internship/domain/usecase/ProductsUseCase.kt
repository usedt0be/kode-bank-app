package ru.kode.base.internship.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.core.domain.BaseUseCase
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.domain.repository.BankAccountRepository
import ru.kode.base.internship.domain.repository.CardRepository
import ru.kode.base.internship.domain.repository.DepositRepository
import javax.inject.Inject

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
  )

  suspend fun fetchBankAccounts()  {
    setState { copy(bankAccountsState = LceState.Loading) }
    try {
      bankAccountRepository.fetchBankAccount()
      setState { copy(bankAccountsState = LceState.Content) }
      withContext(Dispatchers.IO) {
        bankAccountRepository.getBankAccounts()
      }
    } catch (e: Exception) {
      setState { copy(bankAccountsState = LceState.Error("Failed to load accounts")) }
      throw e
    }
  }

  val bankAccounts = bankAccountRepository.getBankAccounts().distinctUntilChanged()

  val bankAccountsState: Flow<LceState> = stateFlow.map { it.bankAccountsState }.distinctUntilChanged()

  suspend fun fetchDeposits()  {
    setState { copy(depositsState = LceState.Loading) }
    try {
      depositsRepository.fetchDeposits()
      setState { copy(depositsState = LceState.Content) }
      withContext(Dispatchers.IO) {
        depositsRepository.getDepositsFromDb()
      }
    } catch (e: Exception) {
      setState { copy(depositsState = LceState.Error("Failed to load deposits")) }
      throw e
    }
  }

  val deposits = depositsRepository.getDepositsFromDb()

  val depositsState: Flow<LceState> = stateFlow.map { it.depositsState }.distinctUntilChanged()

  suspend fun fetchCardDetails(cardId: String) {
      cardRepository.fetchCardDetails()
      cardRepository.getCardDetails(cardId)
      cardRepository.fetchBankAccountBalance()
  }
  suspend fun renameCard(id:String, newName:String) {
    cardRepository.renameCard(id, newName)
  }

  val card = cardRepository.cardFlow

  val cardState: Flow<LceState> = stateFlow.map { it.cardsState }.distinctUntilChanged()

  val accountBalance = cardRepository.balance

}
