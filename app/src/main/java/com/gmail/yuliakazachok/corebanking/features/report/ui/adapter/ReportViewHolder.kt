package com.gmail.yuliakazachok.corebanking.features.report.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.yuliakazachok.corebanking.databinding.ItemReportBinding
import com.gmail.yuliakazachok.corebanking.shared.report.domain.entities.Report
import java.text.SimpleDateFormat
import java.util.*

class ReportViewHolder(private val binding: ItemReportBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ReportViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemReportBinding.inflate(layoutInflater, parent, false)
            return ReportViewHolder(binding)
        }
    }

    fun bind(item: Report) {
        with(binding) {
            dateText.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(item.date)
            volumeText.text = item.volumeIssue.toString()
            percentText.text = item.percent.toString()
            profitText.text = item.totalProfit.toString()
        }
    }
}