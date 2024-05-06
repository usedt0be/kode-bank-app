package ru.kode.base.internship.routing

import ru.kode.base.internship.domain.entity.CardDetailsEntity

sealed class FlowEvent {
  data object UserIdentificationDismissed : FlowEvent()
  data object LoginRequested : FlowEvent()
  data object EnterPasswordDismissed : FlowEvent()
  data object UserLoggedIn : FlowEvent()
  data object CreateNewProduct : FlowEvent()
  data object CheckDeposit : FlowEvent()

  data object BackToHomeScreen: FlowEvent()

  data class OpenCardDetails(val cardId: Long) : FlowEvent()
}
