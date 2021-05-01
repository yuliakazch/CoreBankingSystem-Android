package com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentMaincommissionsBinding
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.presentation.MainCommissionsViewModel
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui.adapter.CommissionAdapter
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainCommissionsFragment : Fragment(), MainCommissionsViewModel.EventListener {

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
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@MainCommissionsFragment)
        setAdapter()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCommissions()
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
        binding.addButton.setOnClickListener {

        }
    }

    override fun goToDetailCommission(commission: Commission) {
        navController.navigate(
            R.id.action_mainCommissionsFragment_to_detailCommissionsFragment,
            Bundle().apply {
                putSerializable(KeysArgsBundle.COMMISSION_DETAIL, commission)
            }
        )
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