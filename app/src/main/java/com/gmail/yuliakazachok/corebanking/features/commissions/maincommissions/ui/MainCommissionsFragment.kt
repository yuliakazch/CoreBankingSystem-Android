package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.databinding.FragmentMaincommissionsBinding
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.presentation.MainCommissionsViewModel
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui.adapter.CommissionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainCommissionsFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainCommissionsViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentMaincommissionsBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaincommissionsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        setAdapter()
        setListeners()
        return binding.root
    }

    private fun setAdapter() {
        val adapter = CommissionAdapter(viewModel)
        binding.listCommissions.adapter = adapter
        viewModel.listCommission.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCommissions()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}