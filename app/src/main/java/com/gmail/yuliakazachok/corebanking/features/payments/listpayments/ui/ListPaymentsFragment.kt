package com.gmail.yuliakazachok.corebanking.features.payments.listpayments.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentListpaymentsBinding
import com.gmail.yuliakazachok.corebanking.features.payments.listpayments.presentation.ListPaymentsViewModel
import com.gmail.yuliakazachok.corebanking.features.payments.listpayments.ui.adapter.PaymentAdapter
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ListPaymentsFragment : Fragment(), ListPaymentsViewModel.EventListener {

    @Inject
    lateinit var viewModel: ListPaymentsViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentListpaymentsBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListpaymentsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@ListPaymentsFragment)
        setAdapter()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPayments(arguments?.getInt(KeysArgsBundle.PAYMENT_LIST))
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPayments(arguments?.getInt(KeysArgsBundle.PAYMENT_LIST))
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setAdapter() {
        val adapter = PaymentAdapter()
        binding.listPayments.adapter = adapter
        viewModel.listPayments.onEach {
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