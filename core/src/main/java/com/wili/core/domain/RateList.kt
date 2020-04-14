package com.wili.core.domain

data class RateList(
    val baseCurrency: String,
    val rates: List<Rate>
)