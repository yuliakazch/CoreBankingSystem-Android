package com.gmail.yuliakazachok.corebanking.features.credits.listcredits.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.features.credits.listcredits.presentation.ListCreditsViewModel
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit

class CreditAdapter(private val viewModel: ListCreditsViewModel) :
    ListAdapter<Credit, CreditViewHolder>(ClientDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreditViewHolder = CreditViewHolder.from(parent, viewModel)

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ClientDiffCallback : DiffUtil.ItemCallback<Credit>() {

    override fun areItemsTheSame(oldItem: Credit, newItem: Credit) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Credit, newItem: Credit) = oldItem == newItem
}