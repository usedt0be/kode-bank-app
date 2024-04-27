package ru.kode.base.internship.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.data.getBankAccountsMocks
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.repository.BankAccountRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class BankAccountsRepositoryImpl @Inject constructor() : BankAccountRepository {
  override fun fetchBankAccount() {
    val accounts = getBankAccountsMocks()
    bankAccounts.update {accounts}
  }

  private var bankAccounts = MutableStateFlow<List<BankAccountEntity>>(emptyList())

  override val bankAccountFlow: Flow<List<BankAccountEntity>>
    get() = bankAccounts

}
