package ru.kode.base.internship.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme


@Composable
fun RenameDialog(
  onConfirm: (String) -> Unit,
  onDismiss: () -> Unit,
  )
{
  var cardName by rememberSaveable { mutableStateOf("") }

  Dialog(onDismissRequest = {  }) {
    Column(
      modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .background(color = AppTheme.colors.backgroundSecondary)
        .size(height = 160.dp, width = 270.dp)
        .fillMaxWidth()
    ) {
      Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxHeight(0.3f)
          .fillMaxWidth()
          .padding(top = 8.dp)
      ) {
        Text(
          text = stringResource(R.string.write_new_name)
        )
      }
      TextField(
        value = cardName,
        onValueChange = { text ->
          cardName = text
        },
        colors = TextFieldDefaults.textFieldColors(
          backgroundColor = AppTheme.colors.backgroundSecondary,
          textColor = AppTheme.colors.textPrimary,
          cursorColor = AppTheme.colors.contendAccentPrimary,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent
        ),
        maxLines = 1,
        textStyle = AppTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxHeight(0.5f)
      )


      Divider(
        modifier = Modifier
          .padding(top = 8.dp)
          .height(1.dp)
          .fillMaxWidth(),
        color = AppTheme.colors.contendTertiary
      )

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
          .background(color = AppTheme.colors.backgroundSecondary)
      ) {

        Text(
          text = stringResource(R.string.cancel_rename_button_text),
          style = AppTheme.typography.bodyMedium,
          modifier = Modifier
            .clickable {
              onDismiss()
              cardName = ""
            },
          color = AppTheme.colors.contendAccentPrimary
        )

        Divider(
          modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
          color = AppTheme.colors.contendTertiary
        )

        Text(
          text = stringResource(R.string.confirm_name_button_text),
          style = AppTheme.typography.bodyMedium,
          modifier = Modifier
            .clickable {
              onConfirm(cardName)
              onDismiss()
            },
          color = AppTheme.colors.contendAccentPrimary
        )
      }
    }
  }
}


