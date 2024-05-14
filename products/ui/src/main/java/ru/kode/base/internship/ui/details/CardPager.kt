package ru.kode.base.internship.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kode.base.internship.products.ui.R
import ru.kode.base.internship.ui.core.uikit.theme.AppTheme
import ru.kode.base.internship.ui.effects.ShimmerCard
import ru.kode.base.internship.ui.entity.CardEntityUi


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardPager(
  cardCount: Int,
  cardDetails: CardEntityUi,
  pageTurned: (Int) -> Unit,
) {
  val pagerState = rememberPagerState(pageCount = {
    cardCount + 1
  })

  LaunchedEffect(pagerState) {
    snapshotFlow { pagerState.settledPage }.collect { page ->
      if (page < cardCount ) {
        pageTurned(page)
      }
    }
  }

  Column {
    HorizontalPager(
      state = pagerState,
      contentPadding = PaddingValues(horizontal = 56.dp),
      pageSize = PageSize.Fill,
      modifier = Modifier,
      beyondBoundsPageCount = 2
    ) { page ->
      val scaleFactor = if (page == pagerState.currentPage) 1f else 0.8f
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(color = AppTheme.colors.backgroundSecondary)
          .graphicsLayer {
            scaleX = scaleFactor
            scaleY = scaleFactor
          }
          .shadow(24.dp),
        contentAlignment = Alignment.Center,
      ) {

        if (page != pagerState.settledPage && page != pagerState.pageCount - 1) {
          ShimmerCard()
        } else {
          if (page == pagerState.pageCount - 1) {
            CreateNewCardItem()
          } else {
            CardDetailsItem(card = cardDetails)
          }
        }
      }
    }

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      repeat(pagerState.pageCount) { iteration ->
        val color = if (pagerState.currentPage == iteration) {
          AppTheme.colors.contendAccentPrimary
        } else {
          AppTheme.colors.textSecondary
        }

        if (iteration != pagerState.pageCount - 1) {
          Box(
            modifier = Modifier
              .padding(start = 10.dp)
              .clip(CircleShape)
              .background(color)
              .size(6.dp)
          )
        } else {
          Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null,
            tint = color,
            modifier = Modifier.padding(start = 10.dp)
          )
        }
      }
    }
  }
}