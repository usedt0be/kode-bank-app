package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.DepositEntity

@Immutable
data class ProductsHomeViewState(
  val bankAccountsState: LceState = LceState.None,
  val depositsState: LceState = LceState.None,
  val bankAccountsData: List<BankAccountEntity> = emptyList(),
  val depositsData: List<DepositEntity> = emptyList(),
  val refreshing: Boolean = false,
  val accountsWithCards: List<BankAccountEntity> = emptyList(),
  val expandedCards: List<CardDetailsEntity> = emptyList(),
  val isLoading: Boolean = depositsState is LceState.Loading && bankAccountsState is LceState.Loading,

)
