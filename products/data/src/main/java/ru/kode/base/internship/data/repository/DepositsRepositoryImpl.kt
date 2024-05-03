package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.mapper.depositMapper
import ru.kode.base.internship.data.mapper.toDepositEntity
import ru.kode.base.internship.data.mapper.toDepositModel
import ru.kode.base.internship.data.network.ProductApi
import ru.kode.base.internship.data.storage.dataSource.DepositDataSource
import ru.kode.base.internship.domain.entity.DepositEntity
import ru.kode.base.internship.domain.repository.DepositRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositsRepositoryImpl @Inject constructor(
  private val api: ProductApi,
  private val depositDataSource: DepositDataSource,
) : DepositRepository {

  private var deposits = MutableStateFlow<List<DepositEntity>>(emptyList())

  override val depositsFlow: Flow<List<DepositEntity>>
    get() = deposits



  override suspend fun fetchDeposits() {
    val depositResponse = api.getDepositList()
    val depositList = mutableListOf<DepositEntity>()

    depositResponse.deposits.forEach { deposit ->
      val depositById = api.getDepositById(deposit.depositId)
      depositList.add(depositMapper(deposit, depositById))
    }

    val depositsSM = depositList.map { it.toDepositModel() }

    depositDataSource.queries.transaction {
      depositsSM.forEach { deposit ->
        depositDataSource.queries.insertDepositObject(
            deposit
        )
      }
    }

  }

  override fun getDepositsFromDb(): Flow<List<DepositEntity>> {
   return  depositDataSource.getAllDeposits().map{depositModels -> depositModels.map { it.toDepositEntity() } }
  }


  override suspend fun getDepositRate(id: String): Flow<String> {
    deposits.value.find { it.depositId == id }.let {
      return flow { it?.rate }
    }
  }
}
