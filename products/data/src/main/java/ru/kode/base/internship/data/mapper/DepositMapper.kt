package ru.kode.base.internship.data.mapper

import ru.kode.base.internship.data.network.entity.DepositByIdResponse
import ru.kode.base.internship.data.network.entity.Deposit
import ru.kode.base.internship.domain.entity.Currency
import ru.kode.base.internship.domain.entity.DepositEntity
import ru.kode.base.internship.domain.entity.Status
import ru.kode.base.internship.products.data.DepositModel


fun depositMapper(
  deposit: Deposit,
  depositByIdResponse: DepositByIdResponse,
): DepositEntity {
  return DepositEntity(
    depositId = deposit.depositId.toString() ,
    name = depositByIdResponse.name,
    rate = depositByIdResponse.rate.toString(),
    status = Status.valueOf(depositByIdResponse.status),
    currencyType = Currency.valueOf(depositByIdResponse.currency),
    balance = depositByIdResponse.balance.toString(),
    closeDate = depositByIdResponse.closeDate
  )
}



fun DepositEntity.toDepositModel(): DepositModel {
  return DepositModel(
    id = depositId.toLong(),
    name = name,
    currency = currencyType.name,
    balance = balance,
    rate = rate,
    closeDate = closeDate,
    status = status.name,
  )
}


fun DepositModel.toDepositEntity(): DepositEntity {
  return DepositEntity(
    depositId = id.toString(),
    name = name,
    currencyType = Currency.valueOf(currency),
    status = Status.valueOf(status),
    balance = balance,
    rate = rate,
    closeDate = closeDate

  )
}

