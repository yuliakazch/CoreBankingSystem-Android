package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.databinding.ItemCommissionBinding
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.domain.entities.Commission

class CommissionViewHolder(
    private val binding: ItemCommissionBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CommissionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCommissionBinding.inflate(layoutInflater, parent, false)
            return CommissionViewHolder(binding)
        }
    }

    fun bind(item: Commission) {
        binding.root.text = item.name
    }
}