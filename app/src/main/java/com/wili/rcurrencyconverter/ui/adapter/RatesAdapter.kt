package com.wili.rcurrencyconverter.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wili.core.domain.Rate
import com.wili.rcurrencyconverter.R
import com.wili.rcurrencyconverter.databinding.ViewHolderCurrencyBinding
import java.math.BigDecimal

class RatesAdapter(private val onRateChangeListener: OnRateChangeListener) :
    RecyclerView.Adapter<RatesAdapter.RateViewHolder>() {
    private val rates = mutableMapOf<String, Rate>()
    private val positionList = ArrayList<String>()
    fun setRates(freshData: List<Rate>) {
        freshData.forEach { rates.put(it.alphabeticCode, it) }
        if (positionList.isEmpty()) {
            positionList.addAll(freshData.map { it.alphabeticCode })
            notifyDataSetChanged()
        } else {
            notifyItemRangeChanged(1, this.positionList.size, this.positionList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewHolderCurrencyBinding>(
            inflater,
            R.layout.view_holder_currency,
            parent,
            false
        )
        return RateViewHolder(binding)
    }

    override fun getItemCount(): Int = positionList.size

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        rates[positionList[position]]?.let {
            holder.bind(it)
        }
    }

    override fun onBindViewHolder(
        holder: RateViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            rates.get(positionList[position])?.let {
                holder.bindValue(it.value)
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    inner class RateViewHolder(private val binding: ViewHolderCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var currRate: String? = null
        fun bind(rate: Rate) {
            currRate = rate.alphabeticCode
            binding.rate = rate
            setListeners(rate)
            bindValue(rate.value)
        }

        fun bindValue(value: BigDecimal) {
            binding.value.setText(value.toString())
        }


        private fun setListeners(rate: Rate) {
            binding.value.setOnFocusChangeListener { v, hasFocus ->
                if (positionList[0] != rate.alphabeticCode && hasFocus) {
                    positionList.removeAt(layoutPosition)
                    positionList.add(0, rate.alphabeticCode)
                    notifyItemMoved(layoutPosition, 0)
                }
            }
            binding.value.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (binding.value.hasFocus()) {
                        val value = s.toString().toFloatOrNull() ?: 0.0f
                        if (value > 0) {
                            binding.value.setTextColor(
                                ContextCompat.getColor(
                                    binding.value.context,
                                    R.color.darkGray
                                )
                            )
                        } else {
                            binding.value.setTextColor(
                                ContextCompat.getColor(
                                    binding.value.context,
                                    R.color.lightGray
                                )
                            )
                        }
                        onRateChangeListener.rateSelected(rate.alphabeticCode, value)
                    }
                }
            })
        }
    }
}
