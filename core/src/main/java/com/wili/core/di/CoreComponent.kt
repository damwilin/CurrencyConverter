package com.wili.core.di.modules

import com.wili.core.data.RateRepository
import com.wili.core.data.network.services.RatesService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
NetworkModule::class,
UsecaseModule::class])
interface CoreComponent{
    fun rateService(): RatesService

    fun rateRepository(): RateRepository
}