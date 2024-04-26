package ru.kode.base.internship.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.component.PrimaryButton

@Composable
fun CreateNewProductButton(onClickCreateProduct: () -> Unit) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxWidth()
  ) {
    PrimaryButton(
      onClick = { /*TODO*/ },
      text = stringResource(id = R.string.create_new_product, "createNewProduct"),
    )
  }
}

@Preview
@Composable
fun CreateNewProductButtonPreview() {
  CreateNewProductButton(onClickCreateProduct = {})
}
