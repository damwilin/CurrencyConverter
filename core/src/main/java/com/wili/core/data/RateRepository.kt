package com.wili.core.data

import com.wili.core.data.network.RatesRemoteDataSource
import com.wili.core.data.network.responses.RateListResponse
import com.wili.core.domain.RateList
import io.reactivex.Single
import javax.inject.Inject

class RateRepository @Inject constructor(private val ratesRemoteDataSource: RatesRemoteDataSource){
    fun getRates(baseCurrency:String): Single<RateListResponse>{
        return ratesRemoteDataSource.getRates(baseCurrency)
    }
}