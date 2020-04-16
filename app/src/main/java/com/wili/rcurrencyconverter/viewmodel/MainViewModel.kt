package com.wili.rcurrencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wili.core.data.responses.RateListResponse
import com.wili.core.domain.Rate
import com.wili.core.usecase.RateUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.math.RoundingMode
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val ratesUseCase: RateUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private var currBaseCurrency = BASE_CURRENCY
    private var currValue = BASE_VALUE

    private val _rates = MutableLiveData<List<Rate>>()
    val rates: LiveData<List<Rate>> = _rates

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private fun fetchRates(baseCurrency: String, value: Float) {
        currBaseCurrency = baseCurrency
        currValue = value
        _loading.postValue(true)
        val observable: Observable<RateListResponse> = ratesUseCase.getRates(baseCurrency)
        val disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ handleResponse(it, baseCurrency, value) }, { handleError(it) })
        compositeDisposable.add(disposable)
    }

    private fun handleResponse(response: RateListResponse, baseCurrency: String, value: Float) {
        _loading.postValue(false)
        val rates = mutableListOf<Rate>()
        rates.add(
            Rate(
                baseCurrency,
                value.toBigDecimal(),
                ratesUseCase.getRateDetails(baseCurrency)
            )
        )
        response.rates.forEach {
            val details = ratesUseCase.getRateDetails(it.key)
            rates.add(
                Rate(
                    it.key, it.value.multiply(
                        value.toBigDecimal()
                    ).setScale(2, RoundingMode.HALF_UP), details
                )
            )
        }
        _rates.postValue(rates)
    }

    private fun handleError(throwable: Throwable) {
        _loading.postValue(false)
        _error.postValue(throwable.message)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    fun getRates(baseCurrency: String = currBaseCurrency, value: Float = currValue) {
        compositeDisposable.clear()
        val disposable =
            Observable.interval(INITIAL_DELAY_IN_SECONDS, INTERVAL_IN_SECONDS, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { fetchRates(baseCurrency, value) },
                    { handleError(it) }
                )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val INTERVAL_IN_SECONDS = 1L
        const val INITIAL_DELAY_IN_SECONDS = 0L
        const val BASE_CURRENCY = "EUR"
        const val BASE_VALUE = 1.0f
    }
}