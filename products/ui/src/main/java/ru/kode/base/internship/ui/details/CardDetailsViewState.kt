package ru.kode.base.internship.ui.details

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardEntity

@Immutable
data class CardDetailsViewState(
  val cardState: LceState = LceState.None,
  val card: CardEntity = Mocks.defaultCardEntity,

  val relatedBankAccountState: LceState = LceState.None,
  val relatedBankAccount: BankAccountEntity = Mocks.defaultBankAccount,
)