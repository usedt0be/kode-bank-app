package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.CardEntity

interface CardRepository {
  suspend fun fetchCards(id: String): Flow<CardEntity>
}
