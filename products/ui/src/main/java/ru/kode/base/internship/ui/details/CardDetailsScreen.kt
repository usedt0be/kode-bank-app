package ru.kode.base.internship.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kode.base.core.rememberViewIntents
import ru.kode.base.core.viewmodel.daggerViewModel
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.component.CardDetailsItem
import ru.kode.base.internship.ui.component.CustomTopAppBar
import ru.kode.base.internship.ui.component.RenameDialog
import ru.kode.base.internship.ui.core.uikit.screen.AppScreen
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

val actions = listOf(
  mapOf(R.drawable.ic_rename_card to "Переименовать карту"),
  mapOf(R.drawable.ic_get_card_details to "Реквизиты счета"),
  mapOf(R.drawable.ic_get_card_info to "Информация о карте"),
  mapOf(R.drawable.ic_get_card to "Выпустить карту"),
  mapOf(R.drawable.ic_block_card to "Заблокировать карту")
)


@Composable
fun CardDetailsScreen(
  cardId: String?,
  viewModel: CardDetailsViewModel = daggerViewModel(),
) = AppScreen(viewModel = viewModel, intents = rememberViewIntents()) { state, intents ->


  LaunchedEffect(key1 = cardId) {
    if (cardId != null) {
      intents.getCardById(cardId)
    }
  }


  if(state.dialogOpened) {
    RenameDialog(
      changeText = { enteredName ->
        intents.renameCard(enteredName)
      },
      enteredName = state.enteredName,
      onClickConfirm = {closeDialog ->
        intents.confirmRename()
        intents.openOrCloseDialog(closeDialog)
      },
      onClickDismiss = { closeDialog, clearTextField ->
        intents.openOrCloseDialog(closeDialog)
        intents.renameCard(clearTextField)
      }
    )
  }


  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = AppTheme.colors.backgroundSecondary)
      .statusBarsPadding()
      .navigationBarsPadding()
      .imePadding()
  )
  {
    CustomTopAppBar(onNavigateBackClick = { intents.navigateOnBack() })

    Box(
      modifier = Modifier.fillMaxWidth(),
      contentAlignment = Alignment.Center
    ) {
      CardDetailsItem(card = state.card, balance = state.bankAccountBalance)
    }

    Spacer(modifier = Modifier.height(40.dp))

    CardActionRow()


    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .padding(top = 24.dp)
        .background(AppTheme.colors.backgroundPrimary),
      verticalArrangement = Arrangement.Top
    ) {
      actions.forEachIndexed { index, map ->
        map.onEach {
          CardActionListItem(onClickCardAction = { actionName ->
            when(actionName) {
               "Переименовать карту" -> intents.openOrCloseDialog(true)
            }
          }, iconResId = it.key, actionName = it.value)
        }
        if (index < actions.lastIndex) {
          Divider(
            color = AppTheme.colors.contendSecondary,
            modifier = Modifier
              .background(AppTheme.colors.backgroundPrimary)
              .padding(start = 40.dp, end = 16.dp)
          )
        }
      }
    }
  }
}





