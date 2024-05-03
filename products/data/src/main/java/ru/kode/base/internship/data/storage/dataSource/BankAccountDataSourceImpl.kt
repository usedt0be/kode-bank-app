package ru.kode.base.internship.data.storage.dataSource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.BankAccountModel
import ru.kode.base.internship.products.data.ProductsDB
import javax.inject.Inject


@ContributesBinding(AppScope::class)
class BankAccountDataSourceImpl @Inject constructor(db: ProductsDB):BankAccountDataSource {

  override val queries = db.bankAccountModelQueries
  override fun getAccounts(): Flow<List<BankAccountModel>> {
    return queries.getAllBankAccounts().asFlow().mapToList(Dispatchers.IO)
  }

  override suspend fun getAccountById(id: Long): BankAccountModel {
    return withContext(Dispatchers.IO) {
      queries.getBankAccountById(id).executeAsOne()
    }
  }

  override suspend fun insertBankAccount(id: Long, status: String, number: String, balance: String, currency: String) {
    withContext(Dispatchers.Default) {
      queries.insertBankAcco(id, status, number, balance, currency)
    }
  }


  override suspend fun insertBankAccountObject(bankAccount: BankAccountModel) {
    withContext(Dispatchers.IO) {
      queries.insertBankAccountObject(BankAccountModel  = bankAccount)
    }
  }
}