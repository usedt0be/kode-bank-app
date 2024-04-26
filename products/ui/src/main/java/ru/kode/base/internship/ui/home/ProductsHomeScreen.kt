package ru.kode.base.internship.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kode.base.core.rememberViewIntents
import ru.kode.base.core.viewmodel.daggerViewModel
import ru.kode.base.internship.core.domain.entity.LceState
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.component.BankAccountItem
import ru.kode.base.internship.ui.component.CreateNewProductButton
import ru.kode.base.internship.ui.component.CustomDivider
import ru.kode.base.internship.ui.component.DepositItem
import ru.kode.base.internship.ui.core.uikit.screen.AppScreen
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.effects.ShimmerEffect
import ru.kode.base.internship.ui.error.LoadingErrorMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsHomeScreen(
  viewModel: ProductsHomeViewModel = daggerViewModel(),
) = AppScreen(
  viewModel = viewModel,
  intents = rememberViewIntents()
) { state, intents ->
  val bankAccounts = state.bankAccountsData
  val deposits = state.depositsData

  val pullRefreshState = rememberPullRefreshState(
    refreshing = false,
    onRefresh = { intents.refreshData() }
  )
  var createButtonIsVisible by remember { mutableStateOf(false) }

  if (state.bankAccountsState is LceState.Content && state.depositsState is LceState.Content) {
    createButtonIsVisible = true
  }

  if (state.bankAccountsState == LceState.Loading || state.depositsState == LceState.Loading) {
    ShimmerEffect()
  } else {
    Box(
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding()
        .imePadding()
        .pullRefresh(pullRefreshState)
    ) {
      LazyColumn {
        item {
          Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 16.dp, top = 47.dp, end = 16.dp)
          ) {
            Text(
              text = stringResource(id = R.string.main_description, "Main"),
              style = AppTheme.typography.title,
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
        }

        fun LazyListScope.bankAccountsSection() {
          item {
            Row(
              modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundSecondary),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = stringResource(id = R.string.bankAccounts, "bankAccounts"),
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(start = 16.dp),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.textTertiary
              )
            }
          }

          itemsIndexed(
            items = bankAccounts
          ) { index, bankAccount ->
            BankAccountItem(
              bankAccount = bankAccount,
              onClickExpand = { cardListExpanded ->
                if (cardListExpanded) {
                  intents.expandCards(bankAccount)
                } else {
                  intents.hideCards(bankAccount)
                }
              }
            )
            if (index != bankAccounts.lastIndex) CustomDivider()
          }
        }

        if (state.bankAccountsState == LceState.Error("Failed to load accounts")) {
          createButtonIsVisible = false
          item {
            LoadingErrorMessage(onClickRefreshFailed = { intents.refreshData() })
          }
        } else {
          bankAccountsSection()
        }

        item {
          Spacer(modifier = Modifier.height(16.dp))
        }

        fun LazyListScope.depositSection() {
          item {
            Row(
              modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundSecondary),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = stringResource(id = R.string.deposits, "deposits"),
                modifier = Modifier.padding(start = 16.dp),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.textTertiary
              )
            }
          }
          itemsIndexed(items = deposits) { index: Int, deposit ->
            DepositItem(deposit = deposit, onClickCheckDeposit = { intents.checkDeposit() })
            if (index != deposits.lastIndex) CustomDivider()
          }
        }

        if (state.depositsState == LceState.Error("Failed to load deposits")) {
          createButtonIsVisible = false
          item {
            LoadingErrorMessage(onClickRefreshFailed = { intents.refreshData() })
          }
        } else {
          depositSection()
        }

        item {
          Spacer(modifier = Modifier.height(16.dp))
        }

        if (createButtonIsVisible) {
          item {
            CreateNewProductButton(onClickCreateProduct = { intents.createNewProduct() })
          }
        }
      }

      PullRefreshIndicator(
        refreshing = state.depositsState == LceState.Loading || state.bankAccountsState == LceState.Loading,
        state = pullRefreshState,
        modifier = Modifier
          .align(Alignment.TopCenter),
        backgroundColor = AppTheme.colors.backgroundSecondary
      )
    }
  }
}
