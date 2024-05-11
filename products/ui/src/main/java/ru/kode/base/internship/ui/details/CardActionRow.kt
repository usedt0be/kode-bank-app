package ru.kode.base.internship.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CardActionRow() {
  var operationButtonIsActive by remember { mutableStateOf(false) }
  var infoButtonIsActive by remember { mutableStateOf(true) }
  var paymentsButtonIsActive by remember { mutableStateOf(false) }

  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 36.dp, end = 36.dp)
  ) {
    Button(
      onClick = {
        operationButtonIsActive = !operationButtonIsActive
        infoButtonIsActive = false
        paymentsButtonIsActive = false
      },
      shape = CircleShape,
      modifier = Modifier.size(56.dp),
      colors = ButtonDefaults.buttonColors(
        backgroundColor = if(operationButtonIsActive) {
          AppTheme.colors.contendAccentTertiary
        } else {
          AppTheme.colors.contendSecondary
        },
        contentColor = if(operationButtonIsActive) {
          AppTheme.colors.contendAccentTertiary
        } else {
          AppTheme.colors.contendSecondary
        }
      )
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_history),
        contentDescription = stringResource(R.string.operation_history_icon),
        tint = if(operationButtonIsActive) {
          AppTheme.colors.contendAccentPrimary
        } else {
          AppTheme.colors.contendAccentTertiary
        }
      )
    }

    Button(
      onClick = {
        infoButtonIsActive = !infoButtonIsActive
        operationButtonIsActive = false
        paymentsButtonIsActive = false
      },
      shape = CircleShape,
      modifier = Modifier.size(56.dp),
      colors = ButtonDefaults.buttonColors(
        backgroundColor = if(infoButtonIsActive) {
        AppTheme.colors.contendAccentTertiary
      } else {
        AppTheme.colors.contendSecondary
      },
        contentColor = if(infoButtonIsActive) {
          AppTheme.colors.contendAccentTertiary
        } else {
          AppTheme.colors.contendSecondary
        }
      )
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_get_card_info),
        contentDescription = stringResource(R.string.card_info_icon),
        tint = if(infoButtonIsActive) {
          AppTheme.colors.contendAccentPrimary
        } else {
          AppTheme.colors.contendAccentTertiary
        }
      )
    }

    Button(
      onClick = {
        paymentsButtonIsActive = !paymentsButtonIsActive
        operationButtonIsActive = false
        infoButtonIsActive = false
      },
      shape = CircleShape,
      modifier = Modifier.size(56.dp),
      colors = ButtonDefaults.buttonColors(
        backgroundColor = if(paymentsButtonIsActive) {
          AppTheme.colors.contendAccentTertiary
        } else {
          AppTheme.colors.contendSecondary
        },
        contentColor = if(paymentsButtonIsActive) {
          AppTheme.colors.contendAccentTertiary
        } else {
          AppTheme.colors.contendSecondary
        }
      )
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_payments),
        contentDescription = stringResource(R.string.card_payments_icon),
        tint = if(paymentsButtonIsActive) {
          AppTheme.colors.contendAccentPrimary
        } else {
          AppTheme.colors.contendAccentTertiary
        }
      )
    }
  }
}


@Composable
@Preview(showBackground = true)
fun CardActionRowPreview() {
  CardActionRow()
}