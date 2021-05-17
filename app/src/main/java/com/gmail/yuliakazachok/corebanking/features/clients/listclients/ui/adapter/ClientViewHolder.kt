package com.gmail.yuliakazachok.corebanking.features.clients.listclients.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.ItemClientBinding
import com.gmail.yuliakazachok.corebanking.features.clients.listclients.presentation.ListClientsViewModel
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientState
import java.text.SimpleDateFormat
import java.util.*

class ClientViewHolder(
    private val binding: ItemClientBinding,
    private val viewModel: ListClientsViewModel
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(
            parent: ViewGroup,
            viewModel: ListClientsViewModel
        ): ClientViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemClientBinding.inflate(layoutInflater, parent, false)
            return ClientViewHolder(binding, viewModel)
        }
    }

    fun bind(item: Client) {
        with(binding) {
            fioText.text = item.fio
            dateText.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(item.dateBirth)
            stateText.text = when (item.state) {
                ClientState.STATE_NOT_TARIFF -> root.resources.getString(R.string.not_tariff)
                ClientState.STATE_NOT_CREDIT -> root.resources.getString(R.string.not_credit)
                ClientState.STATE_YES_CREDIT -> root.resources.getString(R.string.yes_credit)
                else -> root.resources.getString(R.string.locked)
            }
            root.setOnClickListener {
                viewModel.goToDetail(item.numberPassport)
            }
        }
    }
}