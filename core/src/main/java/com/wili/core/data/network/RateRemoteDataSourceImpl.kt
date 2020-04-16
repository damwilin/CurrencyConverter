package com.wili.core.data.network

import com.wili.core.data.network.services.RatesService
import com.wili.core.data.responses.RateListResponse
import io.reactivex.Observable
import javax.inject.Inject

class RateRemoteDataSourceImpl @Inject constructor(private val ratesService: RatesService) :
    RatesRemoteDataSource {
    override fun getRates(baseCurrency: String): Observable<RateListResponse> {
        return ratesService.getLatestRates(baseCurrency)
    }

}