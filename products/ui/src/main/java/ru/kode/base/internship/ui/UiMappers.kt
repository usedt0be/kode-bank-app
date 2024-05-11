package ru.kode.base.internship.ui

import ru.kode.base.internship.domain.Balance
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.ui.entity.CardEntityUi


fun cardUiMapper(balance: Balance, cardEntity: CardDetailsEntity): CardEntityUi {
  return  CardEntityUi(
    balance = balance,
    cardId = cardEntity.cardId.toString(),
    accountId = cardEntity.accountId,
    name = cardEntity.name,
    number = cardEntity.number,
    expiredAt = cardEntity.expiredAt,
    paymentSystem = cardEntity.paymentSystem,
    status = cardEntity.status,
    type = cardEntity.type
  )
}