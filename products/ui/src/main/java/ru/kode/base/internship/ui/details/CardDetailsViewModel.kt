package ru.kode.base.internship.ui.details

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.dimsuz.unicorn2.Machine
import ru.dimsuz.unicorn2.machine
import ru.kode.base.core.BaseViewModel
import ru.kode.base.internship.domain.usecase.ProductsUseCase
import ru.kode.base.internship.routing.FlowEvent
import javax.inject.Inject

@Stable
class CardDetailsViewModel @Inject constructor(
  private val flowEvents: MutableSharedFlow<FlowEvent>,
  private val productsUseCase: ProductsUseCase,
) : BaseViewModel<CardDetailsViewState, CardDetailsIntents>() {

  override fun buildMachine(): Machine<CardDetailsViewState> = machine {
    initial = CardDetailsViewState() to null

    onEach(intent(CardDetailsIntents::getCardById)) {
      action { _, _, cardId ->
        executeAsync {
            productsUseCase.fetchCardDetails(cardId)
        }
      }
    }

    onEach(productsUseCase.bankAccountBalanceState) {
      transitionTo { state, bankAccountBalanceState ->
        state.copy(
          bankAccountBalanceState = bankAccountBalanceState
        )
      }
    }

    onEach(productsUseCase.bankAccountBalance) {
      transitionTo { state, bankAccountBalance ->
        state.copy(
          bankAccountBalance = bankAccountBalance
        )
      }
    }


    onEach(productsUseCase.cardState) {
      transitionTo { state, cardState ->
        state.copy(
          cardState = cardState
        )
      }
    }


    onEach(productsUseCase.card) {
      transitionTo { state, card ->
        state.copy(
          card = card
        )
      }
    }

    onEach(intent(CardDetailsIntents::renameCard)) {
      transitionTo { state, newName ->
        state.copy(
          enteredName = newName
        )
      }
    }

    onEach(intent(CardDetailsIntents::confirmRename)) {
      action { _, newState, _ ->
        executeAsync {
          productsUseCase.renameCard(newState.card.cardId, newState.enteredName)
        }
      }
    }

    onEach(intent(CardDetailsIntents::openOrCloseDialog)) {
      transitionTo { state, openDialog ->
        state.copy(
          dialogOpened = openDialog
        )
      }
    }

    onEach(intent(CardDetailsIntents::navigateOnBack)) {
      action { _, _, _ -> flowEvents.tryEmit(FlowEvent.BackToHomeScreen)  }
    }
  }
}

