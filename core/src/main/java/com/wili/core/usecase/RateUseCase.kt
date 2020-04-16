package com.wili.core.usecase

import com.wili.core.data.RateRepository
import com.wili.core.data.responses.RateListResponse
import com.wili.core.domain.RateDetails
import io.reactivex.Observable
import javax.inject.Inject

class RateUseCase @Inject constructor(private val rateRepository: RateRepository){

    fun getRates(baseCurrency: String): Observable<RateListResponse> =
        rateRepository.getRates(baseCurrency)

    fun getRateDetails(baseCurrency: String): RateDetails? =
        rateRepository.getRateDetails(baseCurrency)
}