package com.wili.core.data.local

import com.wili.core.domain.RateDetails

interface RatesLocalDataSource {
    fun getRateDetails(baseCurrency: String): RateDetails?
}