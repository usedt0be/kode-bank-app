package ru.kode.base.internship.ui.details

import ru.kode.base.core.BaseViewIntents

class CardDetailsIntents: BaseViewIntents() {
  val navigateOnBack = intent("navigateOnBack")

  val renameCard = intent<String>("renameCard")

  val confirmRename = intent("confirmRename")

  val openOrCloseDialog = intent<Boolean>("openDialog")

  val getCardById = intent<String>("cardId")

}