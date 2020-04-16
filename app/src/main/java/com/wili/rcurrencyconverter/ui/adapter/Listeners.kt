package com.wili.rcurrencyconverter.ui.adapter

interface OnRateChangeListener {
    fun rateSelected(alphabeticCode: String, value: Float)
}