package com.gmail.yuliakazachok.corebanking.features.credits.editcredit.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentEditcreditBinding
import com.gmail.yuliakazachok.corebanking.features.credits.editcredit.presentation.EditCreditViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.libraries.utils.closeKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EditCreditFragment : Fragment(), EditCreditViewModel.EventListener {

    @Inject
    lateinit var viewModel: EditCreditViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentEditcreditBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditcreditBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@EditCreditFragment)
        viewModel.numberPassport.value = arguments?.getLong(KeysArgsBundle.CREDIT_EDIT)
        setTariffs()
        setListeners()
        return binding.root
    }

    private fun setTariffs() {
        viewModel.getTariffs()
        viewModel.listTariff.onEach { list ->
            with(binding) {
                this@EditCreditFragment.context?.let { context ->
                    list.forEach {
                        val radioButton = RadioButton(context)
                        radioButton.text = it.name
                        radioButton.id = it.id
                        tariffsRadioGroup.addView(radioButton)
                    }
                }
                tariffsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                    list.onEach { tariff ->
                        if (tariff.id == checkedId) {
                            rateValue.text = tariff.rate.toString()
                            sumField.hint =
                                resources.getString(R.string.from) + tariff.minSum.toString() +
                                        resources.getString(R.string.to) + tariff.maxSum.toString()
                            termField.hint =
                                resources.getString(R.string.from) + tariff.minTerm.toString() +
                                        resources.getString(R.string.to) + tariff.maxTerm.toString()
                        }
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun setListeners() {
        with(binding) {
            buttonGive.setOnClickListener {
                viewModel.saveCredit(
                    tariffsRadioGroup.checkedRadioButtonId,
                    termField.text.toString().toInt(),
                    sumField.text.toString().toInt(),
                    Date(SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).parse(dateOpenField.text.toString()).time)
                )
                goToBack()
            }
            buttonCancel.setOnClickListener {
                goToBack()
            }
        }
    }

    private fun goToBack() {
        closeKeyboard(binding)
        navController.popBackStack()
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