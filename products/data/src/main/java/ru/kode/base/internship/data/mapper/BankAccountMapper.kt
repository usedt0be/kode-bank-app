package ru.kode.base.internship.data.mapper

import ru.kode.base.internship.data.network.response.BankAccount
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.data.BankAccountModel


//fun bankAccountMapper(
//  bankAccount: BankAccount,
//  cardByIdResponse: CardByIdResponse,
//): BankAccountEntity {
//
//  return BankAccountEntity(
//    accountId = bankAccount.accountId.toString(),
//    cards = bankAccount.cards.map {card->
//      cardEntityMapper(cardResponse = card, cardByIdResponse = cardByIdResponse)
//    },
//    status = Status.valueOf(bankAccount.status),
//    number = bankAccount.number,
//    accountBalance = bankAccount.balance.toString(),
//    currency = Currency.valueOf(bankAccount.currency)
//  )
//}

internal fun BankAccountEntity.toBankAccountModel(): BankAccountModel {
  return BankAccountModel(
    id = accountId.toLong(),
    status = status.toString(),
    number = number,
    balance = accountBalance,
    currency = currency.toString(),
  )
}


internal fun BankAccount.toBankAccountEntity():BankAccountEntity {
   return BankAccountEntity(
     accountId = accountId.toString(),
     cards = cards.map { it.toCardEntity(accountId) },
     status = Status.valueOf(status),
     number = number,
     accountBalance = balance.toString(),
     currency = Currency.valueOf(currency)
   )
}


internal fun BankAccountModel.toBankAccountEntity(): BankAccountEntity {
  return BankAccountEntity(
    accountId = id.toString(),
    cards = emptyList() ,
    status = Status.valueOf(status),
    number = number,
    accountBalance = balance,
    currency = Currency.valueOf(currency)
  )
}







