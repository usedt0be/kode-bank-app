package ru.kode.base.internship.ui.home

import kotlinx.coroutines.flow.Flow
import ru.kode.base.core.BaseViewIntents
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardEntity

class ProductsHomeIntents : BaseViewIntents() {
  val load = intent<Flow<List<BankAccountEntity>>>(name = "loadData")
  val refresh = intent(name = "refreshData")
  val checkDeposit = intent(name = "checkDeposit")
  val createNewProduct = intent(name = "CreateNewProduct")
  val hideCards = intent<BankAccountEntity>(name = "expandCards")
  val expandCards = intent<BankAccountEntity>(name = "expandCards")

  val getCardDetails = intent<CardEntity.Id>(name = "getCards")
}
