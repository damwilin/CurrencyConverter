package com.wili.core.di.modules

import com.wili.core.data.RateRepository
import com.wili.core.usecase.RateUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {
    @Singleton
    @Provides
    fun provideRateUseCase(rateRepository: RateRepository): RateUseCase {
        return RateUseCase(rateRepository)
    }
}