package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.presentation.MainCommissionsViewModel

class CommissionAdapter(private val viewModel: MainCommissionsViewModel) :
    ListAdapter<Commission, CommissionViewHolder>(CommissionDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommissionViewHolder = CommissionViewHolder.from(parent)

    override fun onBindViewHolder(holder: CommissionViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}

class CommissionDiffCallback : DiffUtil.ItemCallback<Commission>() {

    override fun areItemsTheSame(oldItem: Commission, newItem: Commission) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Commission, newItem: Commission) = oldItem == newItem
}