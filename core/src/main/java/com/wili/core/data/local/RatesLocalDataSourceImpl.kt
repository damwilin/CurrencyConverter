package com.wili.core.data.local

import com.wili.core.R
import com.wili.core.domain.RateDetails

class RatesLocalDataSourceImpl : RatesLocalDataSource {
    val data = HashMap<String, RateDetails>().apply {
        put("EUR", RateDetails(R.drawable.eu_flag, "Euro"))
        put("ZAR", RateDetails(0, "Rand"))
        put("USD", RateDetails(0, "US Dolar"))
        put("THB", RateDetails(0, "Thai Baht"))
        put("SGD", RateDetails(0, "Singapore Dollar"))
        put("SEK", RateDetails(0, "Swedish Krona"))
        put("RUB", RateDetails(0, "Russian Ruble"))
        put("RON", RateDetails(0, "Romanian leu"))
        put("PLN", RateDetails(0, "Polish zloty"))
        put("PHP", RateDetails(0, "Philippine Peso"))
        put("NZD", RateDetails(0, "New Zealand Dollar"))
        put("NOK", RateDetails(0, "Norwegian Krone"))
        put("MYR", RateDetails(0, "Malaysian Ringgit"))
        put("MXN", RateDetails(0, "Mexican Peso"))
        put("KRW", RateDetails(0, "South Korean won"))
        put("JPY", RateDetails(0, "Japan Yen"))
        put("ISK", RateDetails(0, "Icelandic Krona"))
        put("INR", RateDetails(0, "Indian Rupee"))
        put("ILS", RateDetails(0, "Israeli Shekel"))
        put("IDR", RateDetails(0, "Indonesian Rupiah"))
        put("HUF", RateDetails(0, "Hungarian Forint"))
        put("HRK", RateDetails(0, "Croatian Kuna"))
        put("HKD", RateDetails(0, "Hong Kong Dollar"))
        put("GBP", RateDetails(0, "British Pound"))
        put("DKK", RateDetails(0, "Danish Krone"))
        put("CZK", RateDetails(0, "Czech Koruna"))
        put("CNY", RateDetails(0, "China Yuan"))
        put("CHF", RateDetails(0, "Swiss franc"))
        put("CAD", RateDetails(0, "Canadian Dollar"))
        put("BRL", RateDetails(0, "Brazilian Real"))
        put("AUD", RateDetails(0, "Australian Dollar"))
        put("BGN", RateDetails(0, "Bulgarian Lev"))
    }

    override fun getRateDetails(baseCurrency: String): RateDetails? = data[baseCurrency]
}