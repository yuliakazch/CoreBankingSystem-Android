package com.gmail.yuliakazachok.corebanking.features.clients.listclients.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentListclientsBinding
import com.gmail.yuliakazachok.corebanking.features.clients.listclients.presentation.ListClientsViewModel
import com.gmail.yuliakazachok.corebanking.features.clients.listclients.ui.adapter.ClientAdapter
import com.gmail.yuliakazachok.corebanking.features.commissions.maincommissions.ui.adapter.CommissionAdapter
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.Client
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ListClientsFragment : Fragment(), ListClientsViewModel.EventListener {

    @Inject
    lateinit var viewModel: ListClientsViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentListclientsBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListclientsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@ListClientsFragment)
        setAdapter()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getClients(arguments?.getSerializable(KeysArgsBundle.CLIENT_LIST) as? ClientFilters)
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getClients(arguments?.getSerializable(KeysArgsBundle.CLIENT_LIST) as? ClientFilters)
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setAdapter() {
        val adapter = ClientAdapter(viewModel)
        binding.listClients.adapter = adapter
        viewModel.listClients.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    override fun goToDetailClient(numberPassport: Long) {
        navController.navigate(
            R.id.action_listClientsFragment_to_detailClientFragment,
            Bundle().apply {
                putLong(KeysArgsBundle.CLIENT_DETAIL, numberPassport)
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