package com.wili.core.data

import com.wili.core.data.local.RatesLocalDataSource
import com.wili.core.data.network.RatesRemoteDataSource
import com.wili.core.data.responses.RateListResponse
import com.wili.core.domain.RateDetails
import io.reactivex.Observable
import javax.inject.Inject

class RateRepository @Inject constructor(
    private val ratesRemoteDataSource: RatesRemoteDataSource,
    private val ratesLocalDataSource: RatesLocalDataSource
) {
    fun getRates(baseCurrency: String): Observable<RateListResponse> {
        return ratesRemoteDataSource.getRates(baseCurrency)
    }

    fun getRateDetails(baseCurrency: String): RateDetails? {
        return ratesLocalDataSource.getRateDetails(baseCurrency)
    }
}