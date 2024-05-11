package ru.kode.base.internship.data

import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.DepositEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import kotlin.random.Random

object Mocks {
  val defaultCardEntity = CardDetailsEntity(
      cardId = CardDetailsEntity.Id("00-card"),
      name = if (Random.nextBoolean()) "Пример карты" else "default card",
      type = "Физическая",
      number = "0000 0000 0000 5131",
      paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.Visa,
      accountId = "00",
      status = Status.ACTIVE,
      expiredAt = "2022-04-21T00:00:00Z"
  )

//  val defaultBankAccount = BankAccountEntity(
//    accountBalance = if (Random.nextBoolean()) "00000.00 €" else "00000.00.00 €",
//    currency = Currency.EUR,
//    accountId = "00",
//    number = "",
//    status = Status.ACTIVE,
//    cards = listOf(
//      CardEntity(
//        cardId = 11111111,
//        name = if (Random.nextBoolean()) "Пример карты" else "default card",
//        type = "Физическая",
//        number = "0000 0000 0000 0000",
//        paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.Visa,
//        status = Status.ACTIVE,
//        accountId = "2"
//      )
//    )
//  )
}



