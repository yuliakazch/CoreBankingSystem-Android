package com.gmail.yuliakazachok.corebanking.features.report.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gmail.yuliakazachok.corebanking.shared.report.domain.entities.Report

class ReportAdapter :
    ListAdapter<Report, ReportViewHolder>(ReportDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportViewHolder = ReportViewHolder.from(parent)

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ReportDiffCallback : DiffUtil.ItemCallback<Report>() {

    override fun areItemsTheSame(oldItem: Report, newItem: Report) =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: Report, newItem: Report) = oldItem == newItem
}