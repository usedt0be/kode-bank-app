package ru.kode.base.internship.ui.home

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.internship.core.domain.entity.LceState
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
val defaultUser = User(userId = "", bankAccount = emptyList(), deposits = emptyList())
val data = MutableStateFlow<User>(defaultUser)
val dataState = MutableStateFlow<LceState>(LceState.None)
suspend fun fetchProducts() {
  dataState.value = LceState.Loading
  delay(2000)
  if(Random.nextBoolean()) {
    data.value = profile
    dataState.value = LceState.Content
  } else {
    dataState.value = LceState.Error("Failed to load data")
  }
}
data class User(
  val userId : String,
  val bankAccount: List<BankAccount>,
  val deposits: List<Deposit>
)

data class BankAccount(
  val description: String,
  val cards: List<Card>,
  val accountBalance: String,
  val currency: String,
  val accountId: String,
)

data class Card(
  val description: String,
  val number: String,
  val type: String,
  val paymentSystem: String,
  val isBlocked: Boolean
)


data class Deposit(
  val description: String,
  val currencyType: String,
  val balance: String,
  val rate: String,
  val cardExpiryDate: String
)

private val profile = User(
  userId = "2313",
  bankAccount = listOf(
    BankAccount(
      description = "Счет расчетный",
      accountBalance = "457334.00 ₽",
      currency = "RUB",
      accountId = "31",
      cards = listOf(
        Card(
          description = "Карта зарплатная",
          type = "Физическая",
          number = "4124 4144 5135 5131",
          paymentSystem = "MasterCard",
          isBlocked = false
        ),
        Card(
          description = "Дополнительная карта",
          type = "Физическая",
          number = "4124 4144 5135 5511",
          paymentSystem = "Visa",
          isBlocked = true
        )
      )
    ),
    BankAccount(
      description = "EURO счет",
      accountBalance = "45 334.00 €",
      currency = "EURO",
      accountId = "52",
      cards = listOf(
        Card(
          description = "Карта зарплатная",
          type = "Физическая",
          number = "4124 6932 5135 5131",
          paymentSystem = "Visa",
          isBlocked = false
        ),
        Card(
          description = "Дополнительная карта",
          type = "Физическая",
          number = "4124 5830 5135 0960",
          paymentSystem = "Visa",
          isBlocked = true
        )
      )
    )
  ),
  deposits = listOf(
    Deposit(
      description = "Мой вклад",
      currencyType = "RUB",
      balance = "748 919 ₽",
      rate = "7.65",
      cardExpiryDate = "31.08.2024"
    ),
    Deposit(
      description = "Накопительный",
      currencyType = "USD",
      balance = "4802.3 $",
      rate = "11.05",
      cardExpiryDate = "25.10.2024"
    ),
    Deposit(
      description = "EURO вклад",
      currencyType = "Euro",
      balance = "81 382",
      rate = "7.65",
      cardExpiryDate = "04.08.2024"
    )
  )
)

private val failedProfile = User(
  userId = "2313",
  bankAccount = listOf(
    BankAccount(
      description = "Счет расчетный",
      accountBalance = "457334.00 ₽",
      currency = "RUB",
      accountId = "31",
      cards = listOf(
        Card(
          description = "Карта зарплатная",
          type = "Физическая",
          number = "4124 4144 5135 5131",
          paymentSystem = "MasterCard",
          isBlocked = false
        ),
        Card(
          description = "Дополнительная карта",
          type = "Физическая",
          number = "4124 4144 5135 5511",
          paymentSystem = "Visa",
          isBlocked = true
        )
      )
    ),
    BankAccount(
      description = "EURO счет",
      accountBalance = "45 334.00 €",
      currency = "EURO",
      accountId = "52",
      cards = listOf(
        Card(
          description = "Карта зарплатная",
          type = "Физическая",
          number = "4124 6932 5135 5131",
          paymentSystem = "Visa",
          isBlocked = false
        ),
        Card(
          description = "Дополнительная карта",
          type = "Физическая",
          number = "4124 5830 5135 0960",
          paymentSystem = "Visa",
          isBlocked = true
        )
      )
    )
  ),
  deposits = listOf()
)

//object AccountExamples {
//  val firstAcc: User = User(
//    userId = "2313",
//    bankAccount = listOf(
//      BankAccount(
//        description = "Счет расчетный",
//        accountBalance = "457334.00",
//        cards = listOf(
//          Card(
//            description = "Карта зарплатная",
//            type = "Физическая",
//            number = "4124 4144 5135 5131",
//            paymentSystem = "MasterCard",
//            isBlocked = false
//          ),
//          Card(
//            description = "Дополнительная карта",
//            type = "Физическая",
//            number = "4124 4144 5135 5511",
//            paymentSystem = "Visa",
//            isBlocked = true
//          )
//        )
//      )
//    ),
//    deposits = listOf(
//      Deposit(
//        description = "Мой вклад",
//        currencyType = "Ruble",
//        balance = "748919",
//        rate = "7.65",
//        cardExpiryDate = "31.08.2024"
//      ),
//      Deposit(
//        description = "Накопительный",
//        currencyType = "Usd",
//        balance = "4802.3",
//        rate = "11.05",
//        cardExpiryDate = "25.10.2024"
//      ),
//      Deposit(
//        description = "EURO вклад",
//        currencyType = "Euro",
//        balance = "81382",
//        rate = "7.65",
//        cardExpiryDate = "04.08.2024"
//      )
//    )
//  )
//}

