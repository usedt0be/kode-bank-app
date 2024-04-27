package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.data.getCardMocks
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardsRepositoryImpl @Inject constructor() : CardRepository {

  override val card = MutableStateFlow<CardEntity>(Mocks.defaultCardEntity)

  override suspend fun cardDetails(id: String) {
    val cards = getCardMocks()
    card.update { cards.find { it.cardId.value == id }!! }
  }
}
