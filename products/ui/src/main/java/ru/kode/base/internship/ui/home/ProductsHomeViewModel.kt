package ru.kode.base.internship.ui.home

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.dimsuz.unicorn2.Machine
import ru.dimsuz.unicorn2.machine
import ru.kode.base.core.BaseViewModel
import ru.kode.base.internship.routing.FlowEvent
import javax.inject.Inject

class ProductsHomeViewModel @Inject constructor(
  private val flowEvents: MutableSharedFlow<FlowEvent>,
) : BaseViewModel<ProductsHomeViewState, ProductsHomeIntents>() {
  override fun buildMachine(): Machine<ProductsHomeViewState> = machine {
    initial = ProductsHomeViewState() to {
       viewModelScope.launch { fetchProducts() }
    }

    onEach(intent(ProductsHomeIntents::loadData)) {
      action { _, _, _ -> fetchProducts() }
    }

    onEach(dataState) {
      transitionTo { state, lceState ->
        state.copy(
          dataIsLoading = lceState
        )
      }
    }

    onEach(data) {
      transitionTo { state, data ->
        Log.d("userData", "$data")
        state.copy(
          data = data
        )
      }
    }

    onEach(intent(ProductsHomeIntents::refreshData)) {
      action {_, _, _ -> fetchProducts() }
    }

    onEach(intent(ProductsHomeIntents::createNewProduct)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.CreateNewProduct)
      }
    }
    onEach(intent(ProductsHomeIntents::checkCard)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.CreateNewProduct)
      }
    }

    onEach(intent(ProductsHomeIntents::checkDeposit)) {
      action { _, _, _ ->
        flowEvents.tryEmit(FlowEvent.CreateNewProduct)
      }
    }
  }
}
