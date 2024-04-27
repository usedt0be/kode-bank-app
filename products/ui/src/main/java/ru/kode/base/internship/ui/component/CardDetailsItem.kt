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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun CardItem(card: CardEntity, onClickDetails:(Boolean)-> Unit) {



  Card(
    modifier = Modifier
      .size(height = 160.dp, width = 272.dp)
      .clickable { onClickDetails(true) }
      .shadow(8.dp),
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
      verticalArrangement = Arrangement.Center
    )
    {

      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 24.dp)
      ) {
        Icon(
          painter = when (card.paymentSystem) {
            PaymentSystem.Visa -> painterResource(id = R.drawable.ic_visa)
            PaymentSystem.MasterCard -> painterResource(id = R.drawable.ic_mastercard)
          },
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
          painter = painterResource(id = R.drawable.ic_nfc), contentDescription = "Nfc icon"
        )
      }

      if (card.status == Status.Blocked) {
        Text(
          text = "Заблокирована",
          style = AppTheme.typography.bodyMedium,
          color = AppTheme.colors.indicatorContendError
        )
      } else { Spacer(modifier = Modifier.weight(1f)) }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 24.dp)
      ) {

        Text(
          text = maskCardNumber(card.number),
          modifier = Modifier.alignByBaseline(),
          style = AppTheme.typography.caption1,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = parseDate(card.expireAt),
          modifier = Modifier.alignByBaseline(),
          style = AppTheme.typography.caption1,
          color = AppTheme.colors.textSecondary
        )
      }
    }
  }
}

fun parseDate(input: String): String {
  val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val date = LocalDate.parse(input, dateFormatter)
  val formatter = DateTimeFormatter.ofPattern("MM/dd")
  return date.format(formatter)
}

fun maskCardNumber(input: String): String {
  return "**** ${input.substring(input.length - 4)}"
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
    onClickDetails = {}
  )
}