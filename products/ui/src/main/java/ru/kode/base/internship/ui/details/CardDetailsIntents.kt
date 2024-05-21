package ru.kode.base.internship.ui.details

import ru.kode.base.core.BaseViewIntents

class CardDetailsIntents: BaseViewIntents() {
  val navigateOnBack = intent("navigateOnBack")
  val confirm = intent<String>("confirmRename")
  val openDialog = intent("openDialog")
  val dismissDialog = intent("dismissDialog")
  val openCardDetails = intent<String>("cardId")
  val getNewCardDetails = intent<Int>("getNewCardDetails")
}