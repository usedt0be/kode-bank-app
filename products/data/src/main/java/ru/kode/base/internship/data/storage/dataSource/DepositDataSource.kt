package ru.kode.base.internship.data.storage.dataSource

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.DepositModel
import ru.kode.base.internship.products.data.DepositModelQueries

interface DepositDataSource {

  val queries: DepositModelQueries
  fun getAllDeposits(): Flow<List<DepositModel>>
  suspend fun getDepositById(id: Long): DepositModel

  suspend fun insertDepositObject(deposit:DepositModel)

  suspend fun insertDeposit(
    depositId: Long,
    name: String,
    currency: String,
    balance: String,
    rate: String,
    closeDate: String,
    status: String
    )
}