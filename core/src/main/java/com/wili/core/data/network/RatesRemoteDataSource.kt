package com.wili.core.data.network

import com.wili.core.data.responses.RateListResponse
import io.reactivex.Observable

interface RatesRemoteDataSource {
    fun getRates(baseCurrency: String): Observable<RateListResponse>
}