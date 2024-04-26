package ru.kode.base.internship.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardItem(card: CardEntity, onClickCheckCard: () -> Unit) {
  Row(
    modifier = Modifier
      .height(72.dp)
      .fillMaxWidth()
      .clickable {
        onClickCheckCard()
      }
      .background(color = AppTheme.colors.backgroundSecondary),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      painter = painterResource(id = R.drawable.input),
      contentDescription = stringResource(id = R.string.currency_icon_description, "currencyIcon"),
      modifier = Modifier.padding(start = 16.dp),
      tint = Color.Unspecified
    )

    Spacer(modifier = Modifier.width(16.dp))

    Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {
      Text(
        text = card.name,
        modifier = Modifier,
        style = AppTheme.typography.body2
      )
      Text(
        text = if (card.status == Status.Blocked) {
          stringResource(id = R.string.blocked, "cardIsBlocked")
        } else {
          card.type
        },
        color = if (card.status == Status.Blocked) {
          AppTheme.colors.indicatorContendError
        } else {
          AppTheme.colors.textSecondary
        },
        modifier = Modifier.padding(top = 3.dp),
        style = AppTheme.typography.caption1
      )
    }

    Spacer(modifier = Modifier.weight(1f))

    Icon(
      if (card.paymentSystem == PaymentSystem.MasterCard) {
        painterResource(id = R.drawable.mastercard)
      } else {
        painterResource(id = R.drawable.visa)
      },
      contentDescription = stringResource(id = R.string.card_icon_description, "cardIcon"),
      modifier = Modifier.padding(end = 16.dp),
      tint = Color.Unspecified
    )
  }
}

@Preview
@Composable
fun CardItemPreview() {
  CardItem(
    card = CardEntity(
      cardId = CardEntity.Id("41"),
      name = "Тинькофф платинум)))",
      type = "Физическая",
      paymentSystem = PaymentSystem.Visa,
      number = "5413 4124 4123 4124",
      status = Status.Active,
      expireAt = "02.04.2025",
      accountId = "15135"
    ),
    onClickCheckCard = {}
  )
}
