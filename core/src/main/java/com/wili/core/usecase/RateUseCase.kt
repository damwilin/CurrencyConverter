package com.wili.core.usecase

import com.wili.core.data.RateRepository
import com.wili.core.data.network.responses.RateListResponse
import com.wili.core.domain.RateList
import io.reactivex.Single
import javax.inject.Inject

class RateUseCase @Inject constructor(private val rateRepository: RateRepository){
    
    fun getRates(baseCurrency: String): Single<RateListResponse> = rateRepository.getRates(baseCurrency)
}