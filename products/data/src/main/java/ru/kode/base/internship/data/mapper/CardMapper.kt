package ru.kode.base.internship.data.mapper

import ru.kode.base.internship.data.network.entity.CardByIdResponse
import ru.kode.base.internship.data.network.entity.CardResponse
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.data.CardModel


fun cardEntityMapper(
  cardResponse: CardResponse,
  cardByIdResponse: CardByIdResponse
): CardEntity {
  return CardEntity(
    cardId = CardEntity.Id(cardResponse.card_id),
    accountId = cardByIdResponse.accountId.toString(),
    name = cardByIdResponse.name,
    number = cardByIdResponse.number,
    expiredAt = cardByIdResponse.expiredAt,
    paymentSystem = PaymentSystem.valueOf(cardByIdResponse.paymentSystem),
    status = Status.valueOf(cardByIdResponse.status),
    type = cardResponse.card_type
  )
}


fun CardEntity.toCardModel(): CardModel {
  return CardModel(
    id = cardId.value.toLong(),
    accountId = accountId.toLong(),
    name = name,
    number = number,
    expiredAt = expiredAt,
    paymentSystem = paymentSystem.toString(),
    status = status.toString(),
    type = type
  )
}

fun CardModel.toCardEntity(): CardEntity {
  return CardEntity(
    cardId = CardEntity.Id(id.toString()),
    accountId = accountId.toString(),
    name = name,
    number = number,
    expiredAt = expiredAt,
    paymentSystem = PaymentSystem.valueOf(paymentSystem),
    status = Status.valueOf(status),
    type = type
  )
}