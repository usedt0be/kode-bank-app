package ru.kode.base.internship.data.mapper

import ru.kode.base.internship.data.network.response.CardByIdResponse
import ru.kode.base.internship.data.network.response.CardResponse
import ru.kode.base.internship.domain.entity.CardDetailsEntity
import ru.kode.base.internship.domain.entity.CardEntity
import ru.kode.base.internship.domain.entity.PaymentSystem
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.data.CardDetailsModel
import ru.kode.base.internship.products.data.CardModel


internal fun cardEntityMapper(
  cardResponse: CardResponse,
  cardByIdResponse: CardByIdResponse
): CardDetailsEntity {
  return CardDetailsEntity(
    cardId = CardDetailsEntity.Id(cardResponse.card_id),
    accountId = cardByIdResponse.accountId.toString(),
    name = cardByIdResponse.name,
    number = cardByIdResponse.number,
    expiredAt = cardByIdResponse.expiredAt,
    paymentSystem = PaymentSystem.valueOf(cardByIdResponse.paymentSystem),
    status = Status.valueOf(cardByIdResponse.status),
    type = cardResponse.card_type
  )
}


internal fun cardModelToEntityMapper(
  cardModel: CardModel,
  cardDetailsModel: CardDetailsModel,
): CardDetailsEntity {
  return CardDetailsEntity(
    cardId = CardDetailsEntity.Id(cardDetailsModel.id.toString()),
    accountId = cardDetailsModel.accountId.toString(),
    name = cardDetailsModel.name,
    number = cardDetailsModel.number,
    expiredAt = cardDetailsModel.expiredAt,
    paymentSystem = PaymentSystem.valueOf(cardDetailsModel.paymentSystem),
    status = Status.valueOf(cardDetailsModel.status),
    type = cardModel.type
  )
}

internal fun CardResponse.toCardEntity(accountId: Int) : CardEntity {
  return CardEntity(
    cardId = card_id.toLong(),
    name = name,
    number = number,
    paymentSystem = PaymentSystem.valueOf(payment_system),
    status = Status.valueOf(status),
    type = card_type,
    accountId = accountId.toString()
  )
}

fun CardDetailsEntity.toCardDetailsModel(): CardDetailsModel {
  return CardDetailsModel(
    accountId = accountId.toLong(),
    id = cardId.value.toLong(),
    name = name,
    number = number,
    expiredAt = expiredAt,
    paymentSystem = paymentSystem.toString(),
    status = status.toString(),
  )
}

fun CardModel.toCardEntity(): CardEntity {
  return CardEntity(
    cardId = id,
    name = name,
    number = number,
    paymentSystem = PaymentSystem.valueOf(paymentSystem),
    status = Status.valueOf(status),
    type = type,
    accountId = accountId.toString()
  )
}


internal fun CardEntity.toCardModel(): CardModel {
  return CardModel(
    id = cardId,
    name = name,
    number = number,
    paymentSystem = paymentSystem.toString(),
    status = status.toString(),
    type = type,
    accountId = accountId.toLong()
  )
}





