package ru.kode.base.internship.data.storage.dataSource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.DepositModel
import ru.kode.base.internship.products.data.ProductsDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositDataSourceImpl @Inject constructor(db: ProductsDB): DepositDataSource {

  override val queries = db.depositModelQueries

  override fun getAllDeposits(): Flow<List<DepositModel>> {
    return queries.getAllDeposits().asFlow().mapToList(Dispatchers.IO)
  }

  override suspend fun getDepositById(id: Long): DepositModel{
    return withContext(Dispatchers.IO) {
      queries.getDepositById(id).executeAsOne()
    }
  }

  override suspend fun insertDepositObject(deposit: DepositModel) {
    withContext(Dispatchers.IO) {
      queries.insertDepositObject(deposit)
    }
  }

  override suspend fun insertDeposit(
    depositId: Long,
    name: String,
    currency: String,
    balance: String,
    rate: String,
    closeDate: String,
    status: String
  ) {
    withContext(Dispatchers.IO) {
      queries.insertDeposit(depositId,name,currency,balance, rate, closeDate, status)
    }
  }
}