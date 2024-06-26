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

    onEach(intent(CardDetailsIntents::openCardDetails)) {
      action { _, _, cardId ->
        executeAsync {
          productsUseCase.fetchCardDetails()
          productsUseCase.getCardDetails(cardId)
        }
      }
    }
    onEach(intent(CardDetailsIntents::getNewCardDetails)) {
      action { state, _, page ->
        val cardId = state.relatedCardsIds[page]
        executeAsync {
          productsUseCase.getCardDetails(cardId)
        }
      }
    }

    onEach(productsUseCase.cardState) {
      transitionTo { state, cardDetailsState ->
        state.copy(
          cardDetailsState = cardDetailsState
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

    onEach(productsUseCase.relatedCardsIdsList) {
      transitionTo { state, idsList ->
        state.copy(
          relatedCardsIds = idsList
        )
      }
    }

    onEach(productsUseCase.accountBalance) {
      transitionTo { state, balance->
        state.copy(
          balance = balance
        )
      }
    }

    onEach(intent(CardDetailsIntents::openDialog)) {
      transitionTo { state, _ ->
        state.copy(
          showDialog = !state.showDialog
        )
      }
    }

    onEach(intent(CardDetailsIntents::dismissDialog)) {
      transitionTo { state, _ ->
        state.copy(
          showDialog = !state.showDialog
        )
      }
    }

    onEach(intent(CardDetailsIntents::confirm)) {
      transitionTo { state, _ ->
        state.copy(
          showDialog = !state.showDialog
        )
      }
      action { _, newState, newName ->
        executeAsync {
          productsUseCase.renameCard(newState.card.cardId, newName)
        }
      }
    }





    onEach(intent(CardDetailsIntents::navigateOnBack)) {
      action { _, _, _ -> flowEvents.tryEmit(FlowEvent.BackToHomeScreen)  }
    }
  }
}

