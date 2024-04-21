package ru.kode.base.internship.ui.home

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.core.domain.entity.LceState

@Immutable
data class ProductsHomeViewState(
  val dataIsLoading: LceState = LceState.None,
  val data: User = defaultUser,
  val refreshing: Boolean = false,
)