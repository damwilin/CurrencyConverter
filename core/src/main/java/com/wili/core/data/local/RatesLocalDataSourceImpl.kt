package com.wili.core.data.local

import com.wili.core.R
import com.wili.core.domain.RateDetails

class RatesLocalDataSourceImpl : RatesLocalDataSource {
    val data = HashMap<String, RateDetails>().apply {
        put("EUR", RateDetails(R.drawable.eu_flag, "Euro"))
        put("ZAR", RateDetails(R.drawable.ic_rand, "Rand"))
        put("USD", RateDetails(R.drawable.ic_usd, "US Dolar"))
        put("THB", RateDetails(R.drawable.ic_thb, "Thai Baht"))
        put("SGD", RateDetails(R.drawable.ic_sgd, "Singapore Dollar"))
        put("SEK", RateDetails(R.drawable.ic_sek, "Swedish Krona"))
        put("RUB", RateDetails(R.drawable.ic_rub, "Russian Ruble"))
        put("RON", RateDetails(R.drawable.ic_ron, "Romanian Leu"))
        put("PLN", RateDetails(R.drawable.ic_pln, "Polish Zloty"))
        put("PHP", RateDetails(R.drawable.ic_php, "Philippine Peso"))
        put("NZD", RateDetails(R.drawable.ic_nzd, "New Zealand Dollar"))
        put("NOK", RateDetails(R.drawable.ic_nok, "Norwegian Krone"))
        put("MYR", RateDetails(R.drawable.ic_myr, "Malaysian Ringgit"))
        put("MXN", RateDetails(R.drawable.ic_mxn, "Mexican Peso"))
        put("KRW", RateDetails(R.drawable.ic_krw, "South Korean Won"))
        put("JPY", RateDetails(R.drawable.ic_jpy, "Japan Yen"))
        put("ISK", RateDetails(R.drawable.ic_isk, "Icelandic Krona"))
        put("INR", RateDetails(R.drawable.ic_inr, "Indian Rupee"))
        put("ILS", RateDetails(R.drawable.ic_ils, "Israeli Shekel"))
        put("IDR", RateDetails(R.drawable.ic_idr, "Indonesian Rupiah"))
        put("HUF", RateDetails(R.drawable.ic_huf, "Hungarian Forint"))
        put("HRK", RateDetails(R.drawable.ic_hrk, "Croatian Kuna"))
        put("HKD", RateDetails(R.drawable.ic_hkd, "Hong Kong Dollar"))
        put("GBP", RateDetails(R.drawable.ic_gbp, "British Pound"))
        put("DKK", RateDetails(R.drawable.ic_dkk, "Danish Krone"))
        put("CZK", RateDetails(R.drawable.ic_czk, "Czech Koruna"))
        put("CNY", RateDetails(R.drawable.ic_cny, "China Yuan"))
        put("CHF", RateDetails(R.drawable.ic_chf, "Swiss Franc"))
        put("CAD", RateDetails(R.drawable.ic_cad, "Canadian Dollar"))
        put("BRL", RateDetails(R.drawable.ic_brl, "Brazilian Real"))
        put("AUD", RateDetails(R.drawable.ic_aud, "Australian Dollar"))
        put("BGN", RateDetails(R.drawable.ic_bgn, "Bulgarian Lev"))
    }

    override fun getRateDetails(baseCurrency: String): RateDetails? = data[baseCurrency]
}