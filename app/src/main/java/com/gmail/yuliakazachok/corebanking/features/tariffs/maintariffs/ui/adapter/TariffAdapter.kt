package com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.presentation.MainTariffsViewModel
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff

class TariffAdapter(private val viewModel: MainTariffsViewModel) :
    ListAdapter<Tariff, TariffViewHolder>(TariffDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TariffViewHolder = TariffViewHolder.from(parent)

    override fun onBindViewHolder(holder: TariffViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}

class TariffDiffCallback : DiffUtil.ItemCallback<Tariff>() {

    override fun areItemsTheSame(oldItem: Tariff, newItem: Tariff) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Tariff, newItem: Tariff) = oldItem == newItem
}