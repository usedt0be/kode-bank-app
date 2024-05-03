package ru.kode.base.internship.data

import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.DepositEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import kotlin.random.Random







object Mocks {

  fun getBankAccountsMocks(): List<BankAccountEntity> {
    return listOf(
      BankAccountEntity(
        accountBalance = if (Random.nextBoolean()) "111122.00 ₽" else "222223.00 ₽",
        currency = Currency.RUB,
        accountId = "01-firstAcc",
        number = "5187 1703 5730 1375",
        status = Status.ACTIVE,
        cards = listOf(
          CardEntity(
            cardId = CardEntity.Id("01-card"),
            name = "Карта зарплатная",
            type = "Физическая",
            number = "01-4144135 5131",
            paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
            accountId = "01-firstAcc",
            status = Status.ACTIVE,
            expiredAt = "2022-04-21T00:00:00Z"
          ),
          CardEntity(
            cardId = CardEntity.Id("02-card"),
            name = "Карта зарплатная",
            type = "Физическая",
            number = "0303 4144 5135 5131",
            paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
            accountId = "01-firstAcc",
            status = Status.ACTIVE,
            expiredAt = "2022-04-21T00:00:00Z"
          ),
        )
      ),
      BankAccountEntity(

        accountBalance = if (Random.nextBoolean()) "45 334.00 €" else "51 6352.00 €",
        currency = Currency.EUR,
        accountId = "02-secondACC",
        number = "",
        status = Status.ACTIVE,
        cards = listOf(
          CardEntity(
            cardId = CardEntity.Id("03-card"),
            name = if (Random.nextBoolean()) "Карта зарплатная" else "Студенческая",
            type = "Физическая",
            number = "0404 4144 5135 5131",
            paymentSystem = PaymentSystem.VISA,
            accountId = "02-secondACC",
            status = Status.ACTIVE,
            expiredAt = "2022-04-21T00:00:00Z"
          ),
          CardEntity(
            cardId = CardEntity.Id("04-card"),
            name = if (Random.nextBoolean()) "Карта зарплатная" else "TinkOFF Platinum",
            type = "Физическая",
            number = "4124 4144 5135 5131",
            paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
            accountId = "02-secondACC",
            status = Status.ACTIVE,
            expiredAt = "2022-04-21T00:00:00Z"
          ),
        )
      )
    )
  }

  fun getDepositMocks(): List<DepositEntity> {
    return listOf(
      DepositEntity(
        depositId = "869393",
        name = if (Random.nextBoolean()) "Мой вклад" else "На пылесос",
        currencyType = Currency.RUB,
        balance = "748 919 ₽",
        rate = "7.65",
        closeDate = "31-08-2024",
        status = Status.ACTIVE
      ),
      DepositEntity(
        depositId = "896042",
        name = if (Random.nextBoolean()) "USD вклад" else "На поездку в Улан-Удэ",
        currencyType = Currency.USD,
        balance = if (Random.nextBoolean()) "4802.3 $" else "2122.3 $",
        rate = "11.05",
        closeDate = "25-10-2024",
        status = Status.ACTIVE
      ),
      DepositEntity(
        depositId = "978322",
        name = if (Random.nextBoolean()) "EURO вклад" else "На новый год",
        currencyType = Currency.EUR,
        balance = if (Random.nextBoolean()) "82 382 €" else "31 588 €",
        rate = "7.65",
        closeDate = "04-08-2024",
        status = Status.ACTIVE
      )
    )
  }
  fun getCardMocks(): List<CardEntity> {

    return listOf(
      CardEntity(
        cardId = CardEntity.Id("01-card"),
        name = "Карта зарплатная",
        type = "Физическая",
        number = "01-4144135 5131",
        paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
        accountId = "01-firstAcc",
        status = Status.ACTIVE,
        expiredAt = "2022-04-21T00:00:00Z"
      ),
      CardEntity(
        cardId = CardEntity.Id("02-card"),
        name = if (Random.nextBoolean()) "Карта зарплатная" else "Кредитная",
        type = "Физическая",
        number = "0303 4144 5135 5131",
        paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
        accountId = "01-firstAcc",
        status = Status.ACTIVE,
        expiredAt = "2022-04-21T00:00:00Z"
      ),
      CardEntity(
        cardId = CardEntity.Id("03-card"),
        name = if (Random.nextBoolean()) "Карта зарплатная" else "Студенческая",
        type = "Физическая",
        number = "0404 4144 5135 5131",
        paymentSystem = PaymentSystem.VISA,
        accountId = "02-secondAcc",
        status = Status.ACTIVE,
        expiredAt = "2022-04-21T00:00:00Z"
      ),
      CardEntity(
        cardId = CardEntity.Id("04-card"),
        name = if (Random.nextBoolean()) "Карта зарплатная" else "TinkOFF Platinum",
        type = "Физическая",
        number = "4124 4144 5135 5131",
        paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
        accountId = "02-secondAcc",
        status = Status.ACTIVE,
        expiredAt = "2022-04-21T00:00:00Z"
      )
    )
  }
  val defaultCardEntity = CardEntity(
      cardId = CardEntity.Id("00-card"),
      name = if (Random.nextBoolean()) "Пример карты" else "default card",
      type = "Физическая",
      number = "0000 0000 0000 5131",
      paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
      accountId = "00",
      status = Status.ACTIVE,
      expiredAt = "2022-04-21T00:00:00Z"
  )

  val defaultBankAccount = BankAccountEntity(
    accountBalance = if (Random.nextBoolean()) "00000.00 €" else "00000.00.00 €",
    currency = Currency.EUR,
    accountId = "00",
    number = "",
    status = Status.ACTIVE,
    cards = listOf(
      CardEntity(
        cardId = CardEntity.Id("00-card"),
        name = if (Random.nextBoolean()) "Пример карты" else "default card",
        type = "Физическая",
        number = "0000 0000 0000 0000",
        paymentSystem = if (Random.nextBoolean()) PaymentSystem.MasterCard else PaymentSystem.VISA,
        accountId = "00",
        status = Status.ACTIVE,
        expiredAt = "2022-04-21T00:00:00Z"
      )
    )
  )

  fun renameCard(id:CardEntity.Id,newName:String): CardEntity {
    val cards = getCardMocks()
    cards.find { it.cardId == id }!!.name = newName
    return cards.find { it.cardId == id }!!
  }
}



