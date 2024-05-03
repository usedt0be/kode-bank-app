package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.DepositsEntity

interface DepositRepository {
  val depositsFlow: Flow<List<DepositsEntity>>
  fun fetchDeposits()
  suspend fun getDepositRate(id: String): Flow<String>
}
