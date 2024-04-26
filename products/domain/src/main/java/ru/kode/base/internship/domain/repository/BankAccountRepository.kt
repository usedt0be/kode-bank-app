package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.BankAccountEntity

interface BankAccountRepository {
  val bankAccount: Flow<List<BankAccountEntity>>
  fun updateMocks()
}
