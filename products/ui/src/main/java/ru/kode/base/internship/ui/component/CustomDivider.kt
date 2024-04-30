package ru.kode.base.internship.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme


@Composable

fun CustomDivider(paddingStart: Dp = 0.dp, paddingEnd: Dp = 0.dp ) {
  Row(modifier = Modifier.fillMaxWidth()
    .background(color = AppTheme.colors.backgroundSecondary)
  ) {
    Divider(
      modifier = Modifier
        .padding(start = paddingStart, end = paddingEnd),
      color = AppTheme.colors.contendSecondary,
      thickness = 1.5.dp
    )
  }
}