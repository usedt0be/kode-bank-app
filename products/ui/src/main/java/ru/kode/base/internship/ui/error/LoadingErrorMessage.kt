package ru.kode.base.internship.ui.error

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun LoadingErrorMessage(onClickRefreshFailed:() -> Unit) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
      .fillMaxWidth()
      .height(173.dp)
  ) {
    Text(
      text = stringResource(id = R.string.something_went_wrong),
      style = AppTheme.typography.bodySemibold
    )

    Text(
      text = stringResource(id = R.string.cant_load_content),
      style = AppTheme.typography.body2,
      modifier = Modifier.padding(4.dp),
      textAlign = TextAlign.Center
    )

    Text(
      text = stringResource(id = R.string.update_data),
      style = AppTheme.typography.bodySemibold,
      color = AppTheme.colors.contendAccentPrimary,
      modifier = Modifier.clickable { onClickRefreshFailed() }
    )

  }
}

@Preview(showBackground = true)
@Composable
fun LoadingErrorMessagePreview() {
  LoadingErrorMessage(onClickRefreshFailed = {})
}