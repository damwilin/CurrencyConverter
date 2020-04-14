package com.wili.core.di

import com.wili.core.data.RateRepository
import com.wili.core.data.network.services.RatesService
import com.wili.core.di.modules.NetworkModule
import com.wili.core.di.modules.UsecaseModule
import com.wili.core.usecase.RateUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, UsecaseModule::class])
interface CoreComponent {
    fun rateRepository(): RateRepository

    fun ratesService(): RatesService

    fun usecase(): RateUseCase
}