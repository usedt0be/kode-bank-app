package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.Mocks
import ru.kode.base.internship.domain.entity.DepositsEntity
import ru.kode.base.internship.domain.repository.DepositRepository
import javax.inject.Inject
@ContributesBinding(AppScope::class)
class DepositsRepositoryImpl @Inject constructor() : DepositRepository {
  private val deposits = Mocks.deposits
  override val depositsFlow: Flow<List<DepositsEntity>>
    get() = flowOf(deposits)

  override suspend fun getDepositRate(id: String): Flow<String> {
    deposits.find { it.depositId == id }.let {
      return flow { it?.rate }
    }
  }
}
