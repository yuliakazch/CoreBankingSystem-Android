package com.gmail.yuliakazachok.corebanking.features.clients.listclients.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.ItemClientBinding
import com.gmail.yuliakazachok.corebanking.features.clients.listclients.presentation.ListClientsViewModel
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientStates
import java.text.SimpleDateFormat
import java.util.*

class ClientViewHolder(
    private val binding: ItemClientBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ClientViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemClientBinding.inflate(layoutInflater, parent, false)
            return ClientViewHolder(binding)
        }
    }

    fun bind(item: Client, viewModel: ListClientsViewModel) {
        with(binding) {
            fioText.text = item.fio
            dateText.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(item.dateBirth)
            stateText.text =
                when (viewModel.getStateClient(item.isCredit, item.isTariff, item.countBlockDays)) {
                    ClientStates.STATE_NOT_TARIFF -> root.resources.getString(R.string.not_tariff)
                    ClientStates.STATE_NOT_CREDIT -> root.resources.getString(R.string.not_credit)
                    ClientStates.STATE_YES_CREDIT -> root.resources.getString(R.string.yes_credit)
                    else -> root.resources.getString(R.string.locked)
                }
            root.setOnClickListener {
                viewModel.goToDetail(item.numberPassport)
            }
        }
    }
}