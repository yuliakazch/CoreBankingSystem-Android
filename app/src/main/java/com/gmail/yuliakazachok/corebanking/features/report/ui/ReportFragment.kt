package com.gmail.yuliakazachok.corebanking.features.report.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentReportBinding
import com.gmail.yuliakazachok.corebanking.features.report.presentation.ReportViewModel
import com.gmail.yuliakazachok.corebanking.features.report.ui.adapter.ReportAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ReportFragment : Fragment(), ReportViewModel.EventListener {

    @Inject
    lateinit var viewModel: ReportViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentReportBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@ReportFragment)
        setAdapter()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getReport()
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getReport()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setAdapter() {
        val adapter = ReportAdapter()
        binding.listReport.adapter = adapter
        viewModel.listReport.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    override fun showToastError() {
        Toast
            .makeText(
                requireActivity(),
                requireActivity().resources.getString(R.string.message_error),
                Toast.LENGTH_SHORT
            )
            .show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}