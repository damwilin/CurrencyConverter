package com.wili.rcurrencyconverter.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.wili.rcurrencyconverter.R
import java.math.BigDecimal

@BindingAdapter("setImageRes")
fun setImageRes(view: ImageView, resource: Int) {
    view.setImageResource(resource)
}

@BindingAdapter("setValueColor")
fun setValueColor(view: TextView, value: BigDecimal) {
    if (value > BigDecimal.ZERO) {
        view.setTextColor(ContextCompat.getColor(view.context, R.color.darkGray))
    } else {
        view.setTextColor(ContextCompat.getColor(view.context, R.color.lightGray))
    }
}