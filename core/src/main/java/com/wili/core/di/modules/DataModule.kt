package com.wili.core.di.modules

import com.wili.core.BuildConfig
import com.wili.core.data.RateRepository
import com.wili.core.data.local.RatesLocalDataSource
import com.wili.core.data.local.RatesLocalDataSourceImpl
import com.wili.core.data.network.RateRemoteDataSourceImpl
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
class DataModule {
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
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRatesService(okHttpClient: OkHttpClient): RatesService {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RatesService::class.java)
    }

    @Singleton
    @Provides
    fun provideRatesRemoteDataSource(ratesService: RatesService): RatesRemoteDataSource =
        RateRemoteDataSourceImpl(ratesService)

    @Singleton
    @Provides
    fun provideRatesLocalDataSource(): RatesLocalDataSource = RatesLocalDataSourceImpl()

    @Singleton
    @Provides
    fun provideRateRepository(
        rateRemoteDataSource: RatesRemoteDataSource,
        ratesLocalDataSource: RatesLocalDataSource
    ): RateRepository {
        return RateRepository(rateRemoteDataSource, ratesLocalDataSource)
    }
}