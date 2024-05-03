package ru.kode.base.internship.domain

import ru.kode.base.internship.domain.entity.Currency
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

data class Balance(
  val balance: String,
  val currency: Currency
) {
  fun format(): String {
    return "${parseNumber(balance)} " + when (currency) {
      Currency.RUB -> "₽"
      Currency.USD -> "$"
      Currency.EUR -> "€"
    }
  }
}
  fun parseNumber(input: String): String {
  val number = input.toDouble()
  val symbols = DecimalFormatSymbols.getInstance(Locale.getDefault())
  symbols.groupingSeparator = ' '
  val formatter = DecimalFormat("#,###", symbols)
  return formatter.format(number)
}
