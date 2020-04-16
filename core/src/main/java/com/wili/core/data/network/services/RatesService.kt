package com.wili.core.data.network.services

import com.wili.core.data.network.Urls
import com.wili.core.data.responses.RateListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {
    /**
     * Fetches latest rates for provided base value
     * @param base The base currency for which response is calculated
     */
    @GET(Urls.LATEST_RATE_PATH)
    fun getLatestRates(@Query("base") base: String): Observable<RateListResponse>
}