package com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.databinding.ItemTariffBinding
import com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.presentation.MainTariffsViewModel
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff

class TariffViewHolder(
    private val binding: ItemTariffBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): TariffViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTariffBinding.inflate(layoutInflater, parent, false)
            return TariffViewHolder(binding)
        }
    }

    fun bind(item: Tariff, viewModel: MainTariffsViewModel) {
        binding.root.text = item.name
        binding.root.setOnClickListener {
            viewModel.goToDetail(item.id)
        }
    }
}