package ru.kode.base.internship.ui.home

import kotlinx.coroutines.flow.Flow
import ru.kode.base.core.BaseViewIntents
import ru.kode.base.internship.domain.entity.BankAccountEntity

class ProductsHomeIntents() : BaseViewIntents() {
  val loadData = intent<Flow<List<BankAccountEntity>>>(name = "loadData")
  val refreshData = intent(name = "refreshData")
  val checkCard = intent(name = "checkCard")
  val checkDeposit = intent(name = "checkDeposit")
  val createNewProduct = intent(name = "CreateNewProduct")
  val hideCards = intent<BankAccountEntity>(name = "expandCards")
  val expandCards = intent<BankAccountEntity>(name = "expandCards")
}
