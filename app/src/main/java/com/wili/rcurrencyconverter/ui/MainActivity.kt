package com.wili.rcurrencyconverter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wili.rcurrencyconverter.R
import com.wili.rcurrencyconverter.base.BaseActivity
import com.wili.rcurrencyconverter.databinding.ActivityMainBinding
import com.wili.rcurrencyconverter.ui.adapter.OnRateChangeListener
import com.wili.rcurrencyconverter.ui.adapter.RatesAdapter
import com.wili.rcurrencyconverter.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity(), OnRateChangeListener {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    private val ratesAdapter = RatesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = getViewModel(vmFactory)
        setRecyclerView()
        setViewModelObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRates()
    }

    private fun setViewModelObservers() {
        viewModel.rates.observe(this, Observer {
            ratesAdapter.setRates(it)
        })
        viewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading && ratesAdapter.isEmpty()) {
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }
        })
        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = ratesAdapter
    }

    override fun rateSelected(alphabeticCode: String, value: Float) {
        viewModel.getRates(alphabeticCode, value)
    }

    override fun onPause() {
        super.onPause()
        viewModel.clearDisposable()
    }
}
