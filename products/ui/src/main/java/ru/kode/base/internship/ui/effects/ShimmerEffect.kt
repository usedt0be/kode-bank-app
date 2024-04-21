package ru.kode.base.internship.ui.effects

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme


fun Modifier.shimmer(): Modifier = composed {

  val shimmerColors = listOf(
//    AppTheme.colors.contendTertiary.copy(alpha = 0.6f),
//    AppTheme.colors.contendSecondary.copy(alpha = 0.6f)
    Color(0xFF403A47), Color(0xFF706D76)
  )
  val transition = rememberInfiniteTransition(label = "")

  val transitionAnim = transition.animateFloat(
    initialValue = 0f,
    targetValue = 1000f,
    animationSpec = infiniteRepeatable(
      animation = tween(
        durationMillis = 1000,
        easing = FastOutSlowInEasing
      )
    ), label = ""
  )

  this.background(
    shape = RoundedCornerShape(100),
    brush =
    Brush.linearGradient(
      colors = shimmerColors,
      start = Offset.Zero,
      end = Offset(x = transitionAnim.value, y = transitionAnim.value)
    )
  )
}


@Composable
fun ShimmerLoadingItem() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(72.dp)
      .background(color = AppTheme.colors.backgroundSecondary),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Spacer(modifier = Modifier.width(16.dp))
    Box(
      modifier = Modifier
        .size(40.dp)
        .clip(CircleShape)
        .shimmer()
    )
    Spacer(modifier = Modifier.width(16.dp))

    Column(verticalArrangement = Arrangement.Center) {
      Box(
        modifier = Modifier
          .height(16.dp)
          .fillMaxWidth()
          .shimmer()
      )

      Spacer(modifier = Modifier.height(8.dp))

      Box(
        modifier = Modifier
          .height(12.dp)
          .fillMaxWidth(0.6f)
          .shimmer()
      )
    }
  }
}

@Composable
fun ShimmerScreen() {
  Box(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding()
      .imePadding()
  ) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
      item {
        Row(
          horizontalArrangement = Arrangement.Start,
          modifier = Modifier.padding(start = 16.dp, top = 47.dp, end = 16.dp)
        ) {
          Box(
            modifier = Modifier
              .size(height = 32.dp, width = 160.dp)
              .shimmer()
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
      }
      item {
        Row(
          modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .background(color = AppTheme.colors.backgroundSecondary),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Spacer(modifier = Modifier.width(16.dp))
          Box(
            modifier = Modifier
              .size(height = 14.dp, width = 72.dp)
              .shimmer()
          )
        }
      }
      items(count = 3) {
        ShimmerLoadingItem()
      }
      item {
        Spacer(modifier = Modifier.height(16.dp))
      }
      item {
        Row(
          modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .background(color = AppTheme.colors.backgroundSecondary),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Spacer(modifier = Modifier.width(16.dp))
          Box(
            modifier = Modifier
              .size(height = 14.dp, width = 72.dp)
              .shimmer()
          )
        }
      }
      items(count = 3) {
        ShimmerLoadingItem()
      }

    }
  }
}



@Preview(showBackground = true)
@Composable
fun ShimmerListItemPreview() {
  ShimmerScreen()
}