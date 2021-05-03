package com.gmail.yuliakazachok.corebanking.features.clients.filtersclients.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentFiltersclientsBinding
import com.gmail.yuliakazachok.corebanking.features.clients.filtersclients.presentation.FiltersClientsViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientFilters
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientStates
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FiltersClientsFragment : Fragment() {

    @Inject
    lateinit var viewModel: FiltersClientsViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentFiltersclientsBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersclientsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        setListenersButtons()
        return binding.root
    }

    private fun setListenersButtons() {
        binding.buttonSearchPassport.setOnClickListener {
            navController.navigate(
                R.id.action_filtersClientsFragment_to_detailClientFragment,
                Bundle().apply {
                    putLong(
                        KeysArgsBundle.CLIENT_DETAIL,
                        binding.passportField.text.toString().toLong()
                    )
                }
            )
        }
        binding.buttonSearchParams.setOnClickListener {
            navController.navigate(
                R.id.action_filtersClientsFragment_to_listClientsFragment,
                Bundle().apply {
                    putSerializable(
                        KeysArgsBundle.CLIENT_LIST,
                        ClientFilters(
                            fio = getFio(binding.fioField.text.toString()),
                            year = getYear(binding.yearField.text.toString()),
                            state = getStates()
                        )
                    )
                }
            )
        }
    }

    private fun getFio(stringData: String): String? = if (stringData.isBlank()) null else stringData

    private fun getYear(stringData: String): Int? = if (stringData.isBlank()) null else stringData.toInt()

    private fun getStates(): List<Int>? {
        val list = mutableListOf<Int>()
        if (binding.notTariffCheckBox.isChecked) {
            list.add(ClientStates.STATE_NOT_TARIFF)
        }
        if (binding.notCreditCheckBox.isChecked) {
            list.add(ClientStates.STATE_NOT_CREDIT)
        }
        if (binding.yesCreditCheckBox.isChecked) {
            list.add(ClientStates.STATE_YES_CREDIT)
        }
        if (binding.lockedCheckBox.isChecked) {
            list.add(ClientStates.STATE_BLOCKED)
        }
        return if (list.isEmpty()) null else list
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}