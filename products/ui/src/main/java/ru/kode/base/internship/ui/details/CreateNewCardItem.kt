package ru.kode.base.internship.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme


@Composable
fun CreateNewCardItem() {
  Card(
    modifier = Modifier
      .size(height = 160.dp, width = 272.dp),
    shape = RoundedCornerShape(12.dp),
    backgroundColor = AppTheme.colors.contendAccentPrimary
  ) {
    
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    )
    {
      Icon(
        painter = painterResource(id = R.drawable.white_plus),
        contentDescription = null,
        tint = Color.Unspecified,
      )

      Spacer(modifier = Modifier.heightIn(16.dp))

      Text(
        text = stringResource(R.string.create_new_card),
        style = AppTheme.typography.bodyMedium,
        color = AppTheme.colors.textPrimary
      )
    }
  }
}

@Preview
@Composable
fun CreateNewCardItemPreview() {
  CreateNewCardItem()
}