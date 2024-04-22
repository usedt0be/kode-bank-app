package ru.kode.base.internship.ui.home

import ru.kode.base.core.BaseViewIntents

class ProductsHomeIntents(): BaseViewIntents() {
  val loadData = intent(name = "loadData")
  val refreshData = intent(name = "refreshData")
  val checkCard = intent(name = "checkCard")
  val checkDeposit = intent(name = "checkDeposit")
  val createNewProduct = intent(name = "CreateNewProduct")
}