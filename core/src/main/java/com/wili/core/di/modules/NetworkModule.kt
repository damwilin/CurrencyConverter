package com.wili.core.di.modules

import com.wili.core.data.RateRepository
import com.wili.core.data.network.RateRetrofitRemoteDataSource
import com.wili.core.data.network.RatesRemoteDataSource
import com.wili.core.data.network.Urls
import com.wili.core.data.network.services.RatesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRatesService(): RatesService{
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RatesService::class.java)
    }

    @Singleton
    @Provides
    fun provideRatesRemoteDataSource(ratesService: RatesService): RatesRemoteDataSource = RateRetrofitRemoteDataSource(ratesService)

    @Singleton
    @Provides
    fun provideRateRepository(rateRepository: RateRetrofitRemoteDataSource): RateRepository{
        return RateRepository(rateRepository)
    }
}