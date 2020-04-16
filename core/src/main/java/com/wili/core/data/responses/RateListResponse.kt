package com.wili.core.data.responses

import java.math.BigDecimal

data class RateListResponse(
    val baseCurrency: String,
    val rates: Map<String, BigDecimal>
)