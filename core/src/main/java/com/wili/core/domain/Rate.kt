package com.wili.core.domain

import java.math.BigDecimal

data class Rate(
    val alphabeticCode: String,
    val value: BigDecimal,
    val details: RateDetails?
)

data class RateDetails(
    val flagRes: Int,
    val name: String
)