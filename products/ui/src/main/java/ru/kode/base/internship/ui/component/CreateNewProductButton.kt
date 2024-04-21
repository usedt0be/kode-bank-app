package ru.kode.base.internship.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme


@Composable
fun CreateNewProductButton(onClickCreateProduct:() -> Unit) {
  FloatingActionButton(
    onClick = { onClickCreateProduct()},
    modifier = Modifier
      .height(52.dp)
      .fillMaxWidth()
      .padding(start = 16.dp, end = 16.dp),
    backgroundColor = AppTheme.colors.contendAccentPrimary
  ) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = stringResource(id = R.string.create_new_product),
        style = AppTheme.typography.bodySemibold
      )
    }
  }
}


@Preview
@Composable
fun CreateNewProductButtonPreview() {
  CreateNewProductButton(onClickCreateProduct = {})
}