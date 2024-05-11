package ru.kode.base.internship.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.mapper.depositMapper
import ru.kode.base.internship.data.mapper.toDepositEntity
import ru.kode.base.internship.data.mapper.toDepositModel
import ru.kode.base.internship.data.network.ProductApi
import ru.kode.base.internship.domain.entity.DepositEntity
import ru.kode.base.internship.domain.repository.DepositRepository
import ru.kode.base.internship.products.data.ProductsDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositsRepositoryImpl @Inject constructor(
  private val db: ProductsDB,
  private val api: ProductApi,
) : DepositRepository {

  private var deposits = MutableStateFlow<List<DepositEntity>>(emptyList())

  private val depositQueries = db.depositModelQueries

  override suspend fun fetchDeposits() {
      val depositResponse = api.getDepositList()
      val depositList = mutableListOf<DepositEntity>()

      depositResponse.deposits.forEach { deposit ->
        val depositById = api.getDepositById(deposit.depositId)
        depositList.add(depositMapper(deposit, depositById))
      }

      val depositsSM = depositList.map { it.toDepositModel() }
      depositQueries.transaction {
        depositsSM.forEach { deposit ->
          depositQueries.insertDepositObject(
            deposit
          )
        }
      }
  }

  override fun getDepositsFromDb():Flow<List<DepositEntity>> {
    val depositModelsList = depositQueries.getAllDeposits().asFlow().mapToList(Dispatchers.IO)

    return depositModelsList.map { depositModels -> depositModels.map { it.toDepositEntity() } }
  }


  override suspend fun getDepositRate(id: String): Flow<String> {
    deposits.value.find { it.depositId == id }.let {
      return flow { it?.rate }
    }
  }
}
