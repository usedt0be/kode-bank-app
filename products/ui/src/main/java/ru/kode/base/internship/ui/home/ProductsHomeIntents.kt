package ru.kode.base.internship.ui.home

import ru.kode.base.core.BaseViewIntents
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.CardEntity

class ProductsHomeIntents : BaseViewIntents() {
  val refresh = intent(name = "refreshData")
  val checkDeposit = intent(name = "checkDeposit")
  val createNewProduct = intent(name = "CreateNewProduct")
  val hideCards = intent<BankAccountEntity>(name = "expandCards")
  val expandCards = intent<BankAccountEntity>(name = "expandCards")
  val getCardDetails = intent<CardEntity.Id>(name = "getCards")
}
