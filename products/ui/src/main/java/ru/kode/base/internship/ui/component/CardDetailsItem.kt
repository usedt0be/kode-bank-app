package ru.kode.base.internship.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.entity.CardEntityUi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun CardDetailsItem(card: CardEntityUi) {


  Card(
    modifier = Modifier
      .size(height = 160.dp, width = 272.dp)
      .clickable {}
      .shadow(24.dp),
    shape = RoundedCornerShape(12.dp),
    backgroundColor = AppTheme.colors.backgroundSecondary

  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_brown_card_background),
      contentDescription = "background icon",
      contentScale = ContentScale.FillBounds
    )
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp),
      verticalArrangement = Arrangement.SpaceAround
    )
    {

      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painter = when (card.paymentSystem) {
            PaymentSystem.VISA -> painterResource(id = R.drawable.ic_visa)
            PaymentSystem.Visa -> painterResource(id = R.drawable.ic_visa)
            PaymentSystem.MasterCard -> painterResource(id = R.drawable.ic_mastercard)
          } ,
          contentDescription = "Payment system icon",
          tint = Color.Unspecified,
          modifier = Modifier.alignByBaseline()
        )

        Text(
          text = card.name,
          style = AppTheme.typography.body2,
          modifier = Modifier.padding(start = 16.dp),
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
          painter = painterResource(id = R.drawable.ic_nfc),
          contentDescription = stringResource(R.string.nfc_icon),
          tint = AppTheme.colors.contendAccentTertiary
        )
      }

      if (card.status == Status.ACTIVE) {
        Text(
          text = card.balance.format(),
          style = AppTheme.typography.bodyMedium,
          color = AppTheme.colors.textPrimary
        )
      } else {
        Text(
          text = stringResource(id = R.string.blocked),
          style = AppTheme.typography.bodyMedium,
          color = AppTheme.colors.indicatorContendError
        )
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,

      ) {

        Text(
          text = maskCardNumber(card.number),
          modifier = Modifier.alignByBaseline(),
          style = AppTheme.typography.caption1,
          color = AppTheme.colors.textSecondary
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = parse(card.expiredAt),
          modifier = Modifier.alignByBaseline(),
          style = AppTheme.typography.caption1,
          color = AppTheme.colors.textSecondary
        )
      }
    }
  }
}

fun parse(dateString: String): String {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  val parsedDateTime = LocalDateTime.parse(dateString, formatter)
  return parsedDateTime.format(DateTimeFormatter.ofPattern("MM/yy"))
}

fun maskCardNumber(input: String): String {
  return "**** ${input.substring(input.length - 4)}"
}

@Preview
@Composable
fun CardItemPreview() {
  CardDetailsItem(
    card = CardEntityUi(
      balance = Balance("23", Currency.RUB),
      cardId = "41",
      name = "Тинькофф платинум)))",
      type = "Физическая",
      paymentSystem = PaymentSystem.Visa,
      number = "5413 4124 4123 4124",
      status = Status.ACTIVE,
      expiredAt = "02.04.2025",
      accountId = "15135"
    ),
  )
}