package ru.kode.base.internship.ui.home

import android.util.Log
import androidx.compose.runtime.Stable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
        productsUseCase.fetchBankAccounts()
        productsUseCase.fetchDeposits()
      }
    }

    onEach(intent(ProductsHomeIntents::getCardDetails)) {
      action { _, _, cardId ->
        flowEvents.tryEmit(FlowEvent.OpenCardDetails(cardId))
      }
    }

    onEach(productsUseCase.bankAccountsState) {
      transitionTo { state, bankAccountsState ->
        Log.d("BANK_ACC_STATE", "$bankAccountsState")
        state.copy(
          bankAccountsState = bankAccountsState
        )
      }
    }

    onEach(productsUseCase.bankAccounts) {
      transitionTo { state, bankAccountsData ->
        Log.d("BANK_ACC_DATA", "$bankAccountsData")
        state.copy(
          bankAccountsData = bankAccountsData
        )
      }
    }

    onEach(productsUseCase.depositsState) {
      transitionTo { state, depositsState ->
        Log.d("BANK_DEP_STATE", "$depositsState")
        state.copy(
          depositsState = depositsState
        )
      }
    }

    onEach(productsUseCase.deposits) {
      transitionTo { state, depositsData ->
        Log.d("BANK_DEP_DATA", "$depositsData")
        state.copy(
          depositsData = depositsData
        )
      }
    }

    onEach(intent(ProductsHomeIntents::expandCards)) {
      transitionTo { state, bankAccount ->
        Log.d("BANK_EXP_DATA", "$bankAccount")
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
