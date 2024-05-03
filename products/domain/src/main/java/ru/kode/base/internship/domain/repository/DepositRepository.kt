package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.DepositEntity

interface DepositRepository {
  val depositsFlow : Flow<List<DepositEntity>>

  fun getDepositsFromDb(): Flow<List<DepositEntity>>
  suspend fun fetchDeposits()
  suspend fun getDepositRate(id: String): Flow<String>
}
