package com.gmail.yuliakazachok.corebanking.features.payments.paymentschedule.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentPaymentscheduleBinding
import com.gmail.yuliakazachok.corebanking.features.payments.paymentschedule.presentation.PaymentScheduleViewModel
import com.gmail.yuliakazachok.corebanking.features.payments.paymentschedule.ui.adapter.PaymentScheduleAdapter
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class PaymentScheduleFragment : Fragment(), PaymentScheduleViewModel.EventListener {

    @Inject
    lateinit var viewModel: PaymentScheduleViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentPaymentscheduleBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentscheduleBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@PaymentScheduleFragment)
        setAdapter()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPayments(arguments?.getInt(KeysArgsBundle.PAYMENT_SCHEDULE))
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPayments(arguments?.getInt(KeysArgsBundle.PAYMENT_SCHEDULE))
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setAdapter() {
        val adapter = PaymentScheduleAdapter()
        binding.listPaymentSchedule.adapter = adapter
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