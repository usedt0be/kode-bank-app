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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Composable
fun BankAccountItem(
  bankAccount: BankAccountEntity,
  onClickExpand: (Boolean) -> Unit,
  onClickGetDetails:(CardEntity.Id)-> Unit,
) {
  var cardListExpanded by remember { mutableStateOf(false) }

  val balance = Balance(bankAccount.accountBalance, bankAccount.currency).format()

  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier
        .height(72.dp)
        .fillMaxWidth()
        .background(color = AppTheme.colors.backgroundSecondary),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        painter = when (bankAccount.currency) {
          Currency.RUB -> painterResource(id = R.drawable.ic_rub)
          Currency.USD -> painterResource(id = R.drawable.ic_usd)
          else -> painterResource(id = R.drawable.ic_eur)
        },
        contentDescription = stringResource(id = R.string.currency_icon_description),
        modifier = Modifier.padding(start = 16.dp),
        tint = Color.Unspecified
      )

      Spacer(modifier = Modifier.width(16.dp))

      Column(modifier = Modifier) {
        Text(
          text = stringResource(R.string.bank_account_name),
          modifier = Modifier,
          style = AppTheme.typography.body2
        )
        Text(
          text = balance,
          modifier = Modifier,
          style = AppTheme.typography.body2,
          color = AppTheme.colors.contendAccentPrimary
        )
      }

      Spacer(modifier = Modifier.weight(1f))

      IconButton(
        onClick = {
          cardListExpanded = !cardListExpanded
          onClickExpand(cardListExpanded)
        },
        modifier = Modifier.padding(end = 16.dp),
      ) {
        Icon(
          if (cardListExpanded)
            painterResource(id = R.drawable.expand_button_expanded)
          else
            painterResource(id = R.drawable.expand_button_unexpanded),
          contentDescription = stringResource(id = R.string.expand_icon_description),
          tint = Color.Unspecified
        )
      }
    }

    if (cardListExpanded) {
      bankAccount.cards.forEachIndexed { index, card ->
        CardProductListItem(card = card, onClickDetailsCard = {
          onClickGetDetails(card.cardId)
        })
        if (index != bankAccount.cards.lastIndex) {
          CustomDivider(paddingStart = 72.dp, paddingEnd = 16.dp)
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun BankAccountItemPreview() {
     BankAccountItem(
      bankAccount = BankAccountEntity(
        status = Status.ACTIVE,
        number = "4141",
        accountBalance = "457334.00",
        currency = Currency.RUB,
        accountId = "421",
        cards = listOf(
          CardEntity(
            cardId = CardEntity.Id("41"),
            accountId = "58",
            name = "Карта зарплатная",
            type = "Физическая",
            number = "4124 4144 5135 5131",
            paymentSystem = PaymentSystem.MasterCard,
            status = Status.ACTIVE,
            expiredAt = "12.02.2028"
          ),
          CardEntity(
            cardId = CardEntity.Id("48"),
            accountId = "58",
            name = "Карта зарплатная",
            type = "Физическая",
            number = "4124 4144 5135 5511",
            paymentSystem = PaymentSystem.VISA,
            status = Status.ACTIVE,
            expiredAt = "12.02.2028"
          )
        ),
      ),
    onClickExpand = {} ,
   onClickGetDetails = {}
     )
}
