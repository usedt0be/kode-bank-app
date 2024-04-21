package ru.kode.base.internship.ui.home


import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
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
import ru.kode.base.internship.ui.effects.ShimmerScreen
import ru.kode.base.internship.ui.error.LoadingErrorMessage


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsHomeScreen(
  viewModel: ProductsHomeViewModel = daggerViewModel()) = AppScreen(
  viewModel =viewModel,
  intents = rememberViewIntents()
) { state , intents ->
  val user = state.data

  val pullRefreshState = rememberPullRefreshState(
    refreshing = state.dataIsLoading == LceState.Loading,
    onRefresh = { intents.refreshData()}
  )

  if(state.dataIsLoading == LceState.Loading) {
    ShimmerScreen()
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
              text = "Главная",
              style = AppTheme.typography.title,
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
        }

        item {
          Row(
            modifier = Modifier
              .height(52.dp)
              .fillMaxWidth()
              .background(color = AppTheme.colors.backgroundSecondary),
            verticalAlignment = Alignment.CenterVertically
          )
          {
            Text(
              text = stringResource(id = R.string.accounts),
              modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
              style = AppTheme.typography.body2,
              color = AppTheme.colors.textTertiary
            )
          }
        }

        items(
          user.bankAccount,
          key = { bankAccount -> bankAccount.accountId }
        ) { bankAccount ->
          BankAccountItem(bankAccount = bankAccount, intents = ProductsHomeIntents())

          if (bankAccount != user.bankAccount.last()){
            CustomDivider()
          }
        }

        item {
          Spacer(modifier = Modifier.height(16.dp))
        }

        item {
          Row(
            modifier = Modifier
              .height(52.dp)
              .fillMaxWidth()
              .background(color = AppTheme.colors.backgroundSecondary),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = stringResource(id = R.string.deposits),
              modifier = Modifier.padding(start = 16.dp),
              style = AppTheme.typography.body2,
              color = AppTheme.colors.textTertiary
            )
          }
        }

        items(
          items = user.deposits
        ) { deposit ->
          DepositItem(deposit = deposit, onClickCheckDeposit = {intents.checkDeposit()})

          if (deposit != user.deposits.last()){
              CustomDivider()
          }
        }
        item {
          Spacer(modifier = Modifier.height(16.dp))
        }
        item{
          if(state.dataIsLoading == LceState.Error("Failed to load data")) {
            LoadingErrorMessage(onClickRefreshFailed = {intents.refreshData()})
          } else {
            CreateNewProductButton(onClickCreateProduct = {intents.createNewProduct()})
          }
        }
      }

      PullRefreshIndicator(
        refreshing = state.dataIsLoading == LceState.Loading,
        state = pullRefreshState,
        modifier = Modifier
          .align(Alignment.TopCenter),
        backgroundColor = AppTheme.colors.backgroundSecondary
      )
    }
  }
}

