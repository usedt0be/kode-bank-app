package ru.kode.base.internship.routing

sealed class FlowEvent {
  data object UserIdentificationDismissed : FlowEvent()
  data object LoginRequested : FlowEvent()
  data object EnterPasswordDismissed : FlowEvent()
  data object UserLoggedIn : FlowEvent()
  data object CreateNewProduct: FlowEvent()

  data object CheckCard: FlowEvent()

  data object CheckDeposit: FlowEvent()
}