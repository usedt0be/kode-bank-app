package ru.kode.base.internship.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.DepositEntity
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.core.uikit.theme.MaterialTypography

@Composable
fun DepositItem(deposit: DepositEntity, onClickCheckDeposit: () -> Unit) {

  val balance = Balance(deposit.balance, deposit.currencyType).format()
  Row(
    modifier = Modifier
      .height(72.dp)
      .fillMaxWidth()
      .clickable { onClickCheckDeposit() }
      .background(color = AppTheme.colors.backgroundSecondary),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      painter = when (deposit.currencyType) {
        Currency.RUB -> painterResource(id = R.drawable.ic_rub)
        Currency.USD -> painterResource(id = R.drawable.ic_usd)
        else -> painterResource(id = R.drawable.ic_eur)
      },
      contentDescription = stringResource(id = R.string.currency_icon_description, ),
      modifier = Modifier.padding(start = 16.dp),
      tint = Color.Unspecified
    )

    Spacer(modifier = Modifier.width(16.dp))

    Column(modifier = Modifier.padding(end = 16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = deposit.name,
          modifier = Modifier.alignByBaseline(),
          style = MaterialTypography.body2
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = "Cтавка ${deposit.rate}%",
          modifier = Modifier.alignByBaseline(),
          style = MaterialTypography.caption,
          color = AppTheme.colors.textTertiary
        )
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp)
      ) {
        Text(
          text = balance,
          modifier = Modifier.alignByBaseline(),
          style = MaterialTypography.body2,
          color = AppTheme.colors.contendAccentPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
          text = stringResource(R.string.deposit_close_date, deposit.closeDate),
          modifier = Modifier.alignByBaseline(),
          style = MaterialTypography.caption,
          color = AppTheme.colors.textTertiary
        )
      }
    }
  }
}

@Preview
@Composable
fun DepositItemPreview() {
  DepositItem(
    DepositEntity(
      depositId = "830102",
      name = "Накопительный",
      currencyType = Currency.RUB,
      balance = "31233372.00",
      rate = "8.7",
      closeDate = "31.08.2024",
      status = Status.ACTIVE
    ),
    onClickCheckDeposit = {}
  )
}
