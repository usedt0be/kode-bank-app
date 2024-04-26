package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.repository.CardRepository
import javax.inject.Inject
@ContributesBinding(AppScope::class)
class CardsRepositoryImpl @Inject constructor() : CardRepository {
  private val cards = Mocks.cards
  override suspend fun fetchCards(id: String): Flow<CardEntity> {
    return flow {
      cards.find { id == it.accountId }
    }
  }
}
