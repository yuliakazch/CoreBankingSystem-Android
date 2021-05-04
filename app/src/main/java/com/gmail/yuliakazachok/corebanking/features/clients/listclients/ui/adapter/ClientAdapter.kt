package com.gmail.yuliakazachok.corebanking.features.clients.listclients.ui.adapter

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.features.clients.listclients.presentation.ListClientsViewModel
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client

class ClientAdapter(private val viewModel: ListClientsViewModel) :
    ListAdapter<Client, ClientViewHolder>(ClientDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientViewHolder = ClientViewHolder.from(parent, viewModel)

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ClientDiffCallback : DiffUtil.ItemCallback<Client>() {

    override fun areItemsTheSame(oldItem: Client, newItem: Client) =
        oldItem.numberPassport == newItem.numberPassport

    override fun areContentsTheSame(oldItem: Client, newItem: Client) = oldItem == newItem
}