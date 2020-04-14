package com.wili.core.data.network

import com.wili.core.data.network.responses.RateListResponse
import com.wili.core.domain.RateList
import io.reactivex.Single

interface RatesRemoteDataSource {
    fun getRates(baseCurrency:String): Single<RateListResponse>
}