package com.gmail.yuliakazachok.corebanking.features.payments.makepayment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentMakepaymentBinding
import com.gmail.yuliakazachok.corebanking.features.payments.makepayment.presentation.MakePaymentViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MakePaymentFragment : Fragment(), MakePaymentViewModel.EventListener {

    @Inject
    lateinit var viewModel: MakePaymentViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentMakepaymentBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakepaymentBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@MakePaymentFragment)
        setData()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCommissions()
        viewModel.idCredit.value = arguments?.getInt(KeysArgsBundle.PAYMENT_MAKE)
    }

    private fun setData() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                binding.swipeRefresh.isRefreshing = false
            }
            viewModel.listCommission.onEach { list ->
                this@MakePaymentFragment.context?.let { context ->
                    list.forEach {
                        val radioButton = RadioButton(context)
                        radioButton.text = it.name
                        radioButton.id = it.id
                        commissionsRadioGroup.addView(radioButton)
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
            makePaymentButton.setOnClickListener {
                viewModel.makePayment(
                    Date(SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).parse(dateField.text.toString()).time),
                    sumField.text.toString().toFloat(),
                    commissionsRadioGroup.checkedRadioButtonId
                )
                navController.popBackStack()
            }
        }
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