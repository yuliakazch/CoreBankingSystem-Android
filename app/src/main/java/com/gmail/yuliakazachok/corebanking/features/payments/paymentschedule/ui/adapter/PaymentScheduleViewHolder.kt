package com.gmail.yuliakazachok.corebanking.features.payments.paymentschedule.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.ItemPaymentscheduleBinding
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentScheduleState
import java.text.SimpleDateFormat
import java.util.*

class PaymentScheduleViewHolder(private val binding: ItemPaymentscheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): PaymentScheduleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPaymentscheduleBinding.inflate(layoutInflater, parent, false)
            return PaymentScheduleViewHolder(binding)
        }
    }

    fun bind(item: PaymentSchedule) {
        with(binding) {
            dateText.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(item.date)
            sumText.text = item.sum.toString() + " руб"
            stateText.text = when (item.state) {
                PaymentScheduleState.STATE_NOT_PAID -> root.resources.getString(R.string.not_paid)
                PaymentScheduleState.STATE_PAID -> root.resources.getString(R.string.paid)
                else -> root.resources.getString(R.string.missed)
            }
        }
    }
}