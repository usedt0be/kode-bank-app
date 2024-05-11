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
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardProductListItem(card: CardEntity, onClickDetailsCard:(CardEntity.Id) -> Unit) {
  Row(
    modifier = Modifier
      .height(72.dp)
      .fillMaxWidth()
      .clickable {
        onClickDetailsCard(card.cardId)
      }
      .background(color = AppTheme.colors.backgroundSecondary),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      painter = painterResource(id = R.drawable.input),
      contentDescription = stringResource(id = R.string.currency_icon_description),
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
        text = if (card.status == Status.ACTIVE) {
          stringResource(R.string.active_card)
        } else {
          stringResource(id = R.string.blocked)
        },
        color = if (card.status == Status.ACTIVE) {
          AppTheme.colors.textSecondary
        } else {
          AppTheme.colors.indicatorContendError
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
      contentDescription = stringResource(id = R.string.card_icon_description),
      modifier = Modifier.padding(end = 16.dp),
      tint = Color.Unspecified
    )
  }
}

//@Preview
//@Composable
//fun CardListItemPreview() {
//  CardProductListItem(
//    card = CardEntity(
//      accountId = "26",
//      cardId = 31,
//      name = "Тинькофф платинум)))",
//      type = "Физическая",
//      paymentSystem = PaymentSystem.Visa,
//      number = "5413 4124 4123 4124",
//      status = Status.ACTIVE
//    ),
//    onClickDetailsCard = {}
//  )
//}
