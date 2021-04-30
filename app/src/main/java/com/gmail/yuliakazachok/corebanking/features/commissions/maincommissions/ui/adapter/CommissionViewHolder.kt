package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.databinding.ItemCommissionBinding
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.presentation.MainCommissionsViewModel
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission

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

    fun bind(item: Commission, viewModel: MainCommissionsViewModel) {
        binding.root.text = item.name
        binding.root.setOnClickListener {
            viewModel.goToDetail(item)
        }
    }
}