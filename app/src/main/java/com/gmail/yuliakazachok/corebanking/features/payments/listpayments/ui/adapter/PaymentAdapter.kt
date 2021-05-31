package com.gmail.yuliakazachok.corebanking.features.payments.listpayments.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.shared.payments.domain.entities.Payment

class PaymentAdapter() :
    ListAdapter<Payment, PaymentViewHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentViewHolder = PaymentViewHolder.from(parent)

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PaymentDiffCallback : DiffUtil.ItemCallback<Payment>() {

    override fun areItemsTheSame(oldItem: Payment, newItem: Payment) =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment) = oldItem == newItem
}