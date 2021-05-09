package com.gmail.yuliakazachok.corebanking.features.credits.detailcredit.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentDetailcreditBinding
import com.gmail.yuliakazachok.corebanking.features.credits.detailcredit.presentation.DetailCreditViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.credits.domain.entities.CreditStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailCreditFragment : Fragment(), DetailCreditViewModel.EventListener {

    @Inject
    lateinit var viewModel: DetailCreditViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentDetailcreditBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailcreditBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@DetailCreditFragment)
        setTextAndButtons()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCredit(arguments?.getLong(KeysArgsBundle.CREDIT_DETAIL_PASSPORT))
    }

    private fun setTextAndButtons() {
        viewModel.credit.onEach {
            with(binding) {
                dateValue.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(it.dateOpen)
                rateValue.text = it.rate.toString()
                sumValue.text = it.sum.toString()
                termValue.text = it.term.toString()
                stateValue.text = when (it.state) {
                    CreditStates.STATE_CLOSE -> resources.getString(R.string.close)
                    CreditStates.STATE_ACTIVE -> resources.getString(R.string.open)
                    else -> resources.getString(R.string.expired)
                }
                makePaymentButton.isEnabled = it.state != CreditStates.STATE_CLOSE
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun setListeners() {
        binding.clientButton.setOnClickListener {
            navController.navigate(
                R.id.action_detailCreditFragment_to_detailClientFragment,
                Bundle().apply {
                    putLong(KeysArgsBundle.CLIENT_DETAIL, viewModel.getNumberPassport())
                }
            )
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