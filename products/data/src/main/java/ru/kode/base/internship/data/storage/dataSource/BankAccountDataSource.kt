package ru.kode.base.internship.data.storage.dataSource

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.BankAccountModel
import ru.kode.base.internship.products.data.BankAccountModelQueries

interface BankAccountDataSource{

  val queries:BankAccountModelQueries

  fun getAccounts(): Flow<List<BankAccountModel>>

  suspend fun getAccountById(id: Long): BankAccountModel

  suspend fun insertBankAccount(
    id: Long,
    status: String,
    number: String,
    balance: String,
    currency : String,
    )

  suspend fun  insertBankAccountObject(bankAccount: BankAccountModel)



}