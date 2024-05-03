package ru.kode.base.internship.ui.details

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.domain.entity.CardEntity

@Immutable
data class CardDetailsViewState(
  val cardState: LceState = LceState.None,
  val card: CardEntity = Mocks.defaultCardEntity,


  val enteredName: String = "",

  val bankAccountBalanceState: LceState = LceState.None,
  val bankAccountBalance: String = "88",

  val dialogOpened: Boolean = false,
)