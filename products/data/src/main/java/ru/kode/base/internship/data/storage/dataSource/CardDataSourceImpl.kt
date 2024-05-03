package ru.kode.base.internship.data.storage.dataSource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.CardModel
import ru.kode.base.internship.products.data.ProductsDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardDataSourceImpl @Inject constructor(db: ProductsDB ): CardDataSource {

  override val queries = db.cardModelQueries
  override fun getAllCards(): Flow<List<CardModel>> {
    return queries.getAllCards().asFlow().mapToList(Dispatchers.IO)
  }

  override suspend fun getCardsById(id: Long):CardModel {
    return withContext(Dispatchers.IO) {
      queries.getCardById(id).executeAsOne()
    }
  }

  override suspend fun insertCard(
    id: Long,
    accountId: Long,
    name: String,
    number: String,
    expireAt: String,
    paymentSystem: String,
    status: String,
    type: String,
  ) {
      withContext(Dispatchers.IO) {
        queries.insertCards(id,accountId,name,number,expireAt,paymentSystem,status, type )
      }
  }

  override suspend fun insertCardObject(card: CardModel) {
    withContext(Dispatchers.IO) {
      queries.insertCardObject(CardModel = card)
    }
  }
}