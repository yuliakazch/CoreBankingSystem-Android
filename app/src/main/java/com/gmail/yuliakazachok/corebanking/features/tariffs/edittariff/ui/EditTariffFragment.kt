package com.gmail.yuliakazachok.corebanking.features.tariffs.edittariff.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentEdittariffBinding
import com.gmail.yuliakazachok.corebanking.features.tariffs.edittariff.presentation.EditTariffViewModel
import com.gmail.yuliakazachok.corebanking.libraries.ext.bindTextTwoWayN
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.libraries.utils.closeKeyboard
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditTariffFragment : Fragment(), EditTariffViewModel.EventListener {

    @Inject
    lateinit var viewModel: EditTariffViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentEdittariffBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEdittariffBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.setData(arguments?.getSerializable(KeysArgsBundle.TARIFF_EDIT) as? Tariff)
        bindData()
        setListenersButtons()
        return binding.root
    }

    private fun bindData() {
        binding.tariffField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowName)
        binding.rateField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowRate)
        binding.minSumField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowMinSum)
        binding.maxSumField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowMaxSum)
        binding.minTermField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowMinTerm)
        binding.maxTermField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowMaxTerm)
    }

    private fun setListenersButtons() {
        binding.buttonSave.setOnClickListener {
            viewModel.saveTariff()
            goToBack()
        }
        binding.buttonCancel.setOnClickListener {
            goToBack()
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