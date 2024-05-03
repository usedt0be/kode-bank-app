package ru.kode.base.internship.data.mapper

import ru.kode.base.internship.data.network.entity.BankAccount
import ru.kode.base.internship.data.network.entity.CardByIdResponse
import ru.kode.base.internship.domain.entity.BankAccountEntity
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.data.BankAccountModel


fun bankAccountMapper(
  bankAccount: BankAccount,
  cardByIdResponse: CardByIdResponse,
): BankAccountEntity {

  return BankAccountEntity(
    accountId = bankAccount.accountId.toString(),
    cards = bankAccount.cards.map {card->
      cardEntityMapper(cardResponse = card, cardByIdResponse = cardByIdResponse)
    },
    status = Status.valueOf(bankAccount.status),
    number = bankAccount.number,
    accountBalance = bankAccount.balance.toString(),
    currency = Currency.valueOf(bankAccount.currency)
  )
}


fun BankAccountEntity.toBankAccountModel(): BankAccountModel {
  return BankAccountModel(
    id = accountId.toLong(),
    status = status.toString(),
    number = number,
    balance = accountBalance,
    currency = currency.toString(),
  )
}







