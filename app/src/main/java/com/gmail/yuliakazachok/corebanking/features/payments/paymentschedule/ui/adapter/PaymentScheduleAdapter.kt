package com.gmail.yuliakazachok.corebanking.features.payments.paymentschedule.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.PaymentSchedule

class PaymentScheduleAdapter() :
    ListAdapter<PaymentSchedule, PaymentScheduleViewHolder>(PaymentScheduleDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentScheduleViewHolder = PaymentScheduleViewHolder.from(parent)

    override fun onBindViewHolder(holder: PaymentScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PaymentScheduleDiffCallback : DiffUtil.ItemCallback<PaymentSchedule>() {

    override fun areItemsTheSame(oldItem: PaymentSchedule, newItem: PaymentSchedule) =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: PaymentSchedule, newItem: PaymentSchedule) = oldItem == newItem
}