package com.wili.core.data.network

import com.wili.core.data.network.responses.RateListResponse
import com.wili.core.data.network.services.RatesService
import io.reactivex.Single
import javax.inject.Inject

class RateRetrofitRemoteDataSource @Inject constructor(private val ratesService: RatesService):
    RatesRemoteDataSource {
    override fun getRates(baseCurrency: String): Single<RateListResponse> {
        return ratesService.getLatestRates(baseCurrency)
    }

}