package ru.kode.base.internship.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.home.BankAccount
import ru.kode.base.internship.ui.home.Card
import ru.kode.base.internship.ui.home.ProductsHomeIntents


@Composable
fun BankAccountItem(bankAccount: BankAccount, intents: ProductsHomeIntents) {
  val cards = bankAccount.cards
  val cardListExpanded = remember { mutableStateOf(false) }
  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier
        .height(72.dp)
        .fillMaxWidth()
        .background(color = AppTheme.colors.backgroundSecondary),
      verticalAlignment = Alignment.CenterVertically
    ) {

      Icon(
        painter = when(bankAccount.currency) {
          "RUB" -> painterResource(id = R.drawable.ic_rub)
          "USD" -> painterResource(id = R.drawable.ic_usd)
          else -> painterResource(id = R.drawable.ic_eur)
        },
        contentDescription ="Russian ruble icon",
        modifier = Modifier.padding(start = 16.dp),
        tint = Color.Unspecified
      )

      Spacer(modifier = Modifier.width(16.dp))

      Column(modifier = Modifier) {
        Text(
          text = bankAccount.description,
          modifier = Modifier,
          style = AppTheme.typography.body2
        )
        Text(
          text = bankAccount.accountBalance,
          modifier = Modifier,
          style = AppTheme.typography.body2,
          color = AppTheme.colors.contendAccentPrimary
        )
      }

      Spacer(modifier = Modifier.weight(1f))

      IconButton(
        onClick = {
          cardListExpanded.value = !cardListExpanded.value
        },
       modifier = Modifier.padding(end = 16.dp),
      ) {
        Icon(
          if(cardListExpanded.value) painterResource(id = R.drawable.expand_button_expanded)
          else painterResource(id = R.drawable.expand_button_unexpanded),
          contentDescription = "Expand Button",
          tint = Color.Unspecified
        )
      }
    }

    if(cardListExpanded.value) {
      for (card in cards) {
        CardItem(card = card, onClickCheckCard = {intents.checkCard()})
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun BankAccountItemPreview() {
  val intents = ProductsHomeIntents()
  BankAccountItem(
    bankAccount = BankAccount(
      description = "Счет расчетный",
      accountBalance = "457334.00",
      currency = "RUB",
      accountId = "421",
      cards = listOf(
        Card(
          description = "Карта зарплатная",
          type = "Физическая",
          number = "4124 4144 5135 5131",
          paymentSystem = "MasterCard",
          isBlocked = false
        ),
        Card(
          description = "Дополнительная карта",
          type = "Физическая",
          number = "4124 4144 5135 5511",
          paymentSystem = "Visa",
          isBlocked = true
        )
      ),
    ), intents
  )
}