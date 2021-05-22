package com.gmail.yuliakazachok.corebanking.features.credits.listcredits.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.ItemCreditBinding
import com.gmail.yuliakazachok.corebanking.features.credits.listcredits.presentation.ListCreditsViewModel
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.Credit
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditState
import java.text.SimpleDateFormat
import java.util.*

class CreditViewHolder(
    private val binding: ItemCreditBinding,
    private val viewModel: ListCreditsViewModel
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(
            parent: ViewGroup,
            viewModel: ListCreditsViewModel
        ): CreditViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCreditBinding.inflate(layoutInflater, parent, false)
            return CreditViewHolder(binding, viewModel)
        }
    }

    fun bind(item: Credit) {
        with(binding) {
            rateText.text = item.rate.toString()
            sumText.text = item.sum.toString()
            termText.text = item.term.toString()
            dateText.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(item.dateOpen)
            stateText.text = when (item.state) {
                CreditState.STATE_CLOSE -> root.resources.getString(R.string.close)
                CreditState.STATE_ACTIVE -> root.resources.getString(R.string.open)
                else -> root.resources.getString(R.string.expired)
            }
            root.setOnClickListener {
                viewModel.goToDetail(item.id)
            }
        }
    }
}