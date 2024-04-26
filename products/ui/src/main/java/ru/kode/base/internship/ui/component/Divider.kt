package ru.kode.base.internship.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme

@Composable
fun CustomDivider() {
  Row(
    modifier = Modifier.fillMaxWidth()
      .background(color = AppTheme.colors.backgroundSecondary)
  ) {
    Divider(
      modifier = Modifier
        .padding(start = 72.dp, end = 16.dp),
      color = AppTheme.colors.contendSecondary,
      thickness = 1.5.dp
    )
  }
}
