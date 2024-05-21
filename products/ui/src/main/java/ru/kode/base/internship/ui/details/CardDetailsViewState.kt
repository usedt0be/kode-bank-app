package ru.kode.base.internship.ui.details

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.Currency

@Immutable
data class CardDetailsViewState(
  val cardDetailsState: LceState = LceState.None,
  val card: CardDetailsEntity = Mocks.defaultCardEntity,
  val enteredName: String = "",
  val balance: Balance = Balance(balance = "33", Currency.RUB),
  val showDialog: Boolean = false,
  val relatedCardsIds: List<String> = emptyList(),
  val switchedCards: List<Int> = emptyList()
)