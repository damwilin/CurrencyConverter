package com.wili.rcurrencyconverter.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewModel> getViewModel(
        viewModelFactory: ViewModelProvider.Factory
    ): T =
        ViewModelProvider(this, viewModelFactory)[T::class.java]

}