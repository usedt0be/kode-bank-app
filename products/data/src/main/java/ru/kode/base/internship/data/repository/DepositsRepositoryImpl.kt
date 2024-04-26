package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.getDepositMocks
import ru.kode.base.internship.domain.entity.DepositsEntity
import ru.kode.base.internship.domain.repository.DepositRepository
import javax.inject.Inject
@ContributesBinding(AppScope::class)
class DepositsRepositoryImpl @Inject constructor() : DepositRepository {

  override fun updateDepositMocks() {
    deposits.update { getDepositMocks() }
  }

  private var deposits = MutableStateFlow<List<DepositsEntity>>(emptyList())

  override val depositsFlow: Flow<List<DepositsEntity>>
    get() = deposits

  override suspend fun getDepositRate(id: String): Flow<String> {
    deposits.value.find { it.depositId == id }.let {
      return flow { it?.rate }
    }
  }
}
