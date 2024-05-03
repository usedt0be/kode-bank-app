package ru.kode.base.internship.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme


@Composable
fun CardDetailsActionListItem(onClickCardAction:(String) -> Unit, iconId: Int, actionName: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .heightIn(min = 56.dp)
      .clickable { onClickCardAction(actionName) }
      .padding(16.dp),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  )
  {
    Icon(
      painter = painterResource(id = iconId) ,
      contentDescription = "",
      modifier = Modifier.alignByBaseline()
    )
    
    Text(
      text = actionName,
      style = AppTheme.typography.body2,
      color = AppTheme.colors.contendTertiary,
      modifier = Modifier.alignByBaseline()
        .padding(start = 16.dp)
    )
  }
}


@Preview
@Composable
fun CardActionItemPreview() {
  CardDetailsActionListItem(
    onClickCardAction = {},
    iconId =R.drawable.ic_rename_card,
    "Переименовать карту"
  )
}