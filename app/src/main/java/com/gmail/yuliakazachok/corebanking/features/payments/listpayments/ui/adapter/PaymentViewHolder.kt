package com.gmail.yuliakazachok.corebanking.features.payments.listpayments.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.databinding.ItemPaymentBinding
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment
import java.text.SimpleDateFormat
import java.util.*

class PaymentViewHolder(private val binding: ItemPaymentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): PaymentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPaymentBinding.inflate(layoutInflater, parent, false)
            return PaymentViewHolder(binding)
        }
    }

    fun bind(item: Payment) {
        with(binding) {
            dateText.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(item.date)
            sumText.text = item.sum.toString() + " руб"
            interestText.text = item.interest.toString() + " %"
        }
    }
}