package ru.kode.base.internship.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.domain.entity.BankAccountEntity

interface BankAccountRepository {
  suspend fun fetchBankAccount()
  fun getBankAccounts(): Flow<List<BankAccountEntity>>
}
