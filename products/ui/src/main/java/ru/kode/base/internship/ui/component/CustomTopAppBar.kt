package ru.kode.base.internship.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CustomTopAppBar(onClickNavigateOnBack:() -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(color = AppTheme.colors.backgroundSecondary)
      .heightIn(32.dp)
  ) {
    IconButton(
      onClick = { onClickNavigateOnBack() },
      modifier = Modifier
        .align(Alignment.CenterStart)
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = stringResource(R.string.back_to_home_screen_icon)
      )
    }
    Text(
      text = "Карты",
      modifier = Modifier.align(Alignment.Center),
      style = AppTheme.typography.bodySemibold,
      color = AppTheme.colors.textPrimary
    )
  }
}