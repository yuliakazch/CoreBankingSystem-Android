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
                        viewModel.getClientFilters(
                            binding.fioField.text.toString(),
                            binding.yearField.text.toString(),
                            binding.notTariffCheckBox.isChecked,
                            binding.notCreditCheckBox.isChecked,
                            binding.yesCreditCheckBox.isChecked,
                            binding.lockedCheckBox.isChecked
                        )
                    )
                }
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}