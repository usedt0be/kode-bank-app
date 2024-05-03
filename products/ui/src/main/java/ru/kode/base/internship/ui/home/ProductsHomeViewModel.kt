package ru.kode.base.internship.ui.home

import android.util.Log
import androidx.compose.runtime.Stable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.dimsuz.unicorn2.Machine
import ru.dimsuz.unicorn2.machine
import ru.kode.base.core.BaseViewModel
import ru.kode.base.internship.domain.usecase.ProductsUseCase
import ru.kode.base.internship.routing.FlowEvent
import javax.inject.Inject

@Stable
class ProductsHomeViewModel @Inject constructor(
  private val flowEvents: MutableSharedFlow<FlowEvent>,
  private val productsUseCase: ProductsUseCase,
) : BaseViewModel<ProductsHomeViewState, ProductsHomeIntents>() {
  override fun buildMachine(): Machine<ProductsHomeViewState> = machine {
    initial = ProductsHomeViewState() to {
      executeAsync {
        productsUseCase.fetchDeposits()
        productsUseCase.fetchBankAccounts()
      }
    }

    onEach(intent(ProductsHomeIntents::getCardDetails)) {
      action { _, _, cardId ->
        flowEvents.tryEmit(FlowEvent.GetCardDetails(cardId))
      }
    }

    onEach(productsUseCase.bankAccountsState) {
      transitionTo { state, bankAccountsState ->
        state.copy(
          bankAccountsState = bankAccountsState
        )
      }
    }

    onEach(productsUseCase.bankAccounts) {
      transitionTo { state, bankAccountsData ->
        state.copy(
          bankAccountsData = bankAccountsData
        )
      }
    }

    onEach(productsUseCase.depositsState) {
      transitionTo { state, depositsState ->
        state.copy(
          depositsState = depositsState
        )
      }
    }

    onEach(productsUseCase.deposits) {
      transitionTo { state, depositsData ->
        state.copy(
          depositsData = depositsData
        )
      }
    }

    onEach(intent(ProductsHomeIntents::expandCards)) {
      transitionTo { state, bankAccount ->
        state.copy(
          accountsWithCards = state.accountsWithCards + bankAccount
        )
      }
    }

    onEach(intent(ProductsHomeIntents::hideCards)) {
      transitionTo { state, bankAccount ->
        state.copy(
          accountsWithCards = state.accountsWithCards - bankAccount
        )
      }
    }

    onEach(intent(ProductsHomeIntents::refresh)) {
      action { _, _, _ ->
        viewModelScope.launch(Dispatchers.IO) {
          executeAsync {
            productsUseCase.fetchBankAccounts()
            productsUseCase.fetchDeposits()
          }
        }
      }
    }


    onEach(intent(ProductsHomeIntents::checkDeposit)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.CreateNewProduct)
      }
    }
  }
}
