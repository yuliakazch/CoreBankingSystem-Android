package com.gmail.yuliakazachok.corebanking.features.commissions.editcommission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentEditcommissionBinding
import com.gmail.yuliakazachok.corebanking.features.commissions.editcommission.presentation.EditCommissionViewModel
import com.gmail.yuliakazachok.corebanking.libraries.ext.bindTextTwoWayN
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.libraries.utils.closeKeyboard
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditCommissionFragment : Fragment(), EditCommissionViewModel.EventListener {

    @Inject
    lateinit var viewModel: EditCommissionViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentEditcommissionBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditcommissionBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.setData(arguments?.getSerializable(KeysArgsBundle.COMMISSION_EDIT) as? Commission)
        bindData()
        setListenersButtons()
        return binding.root
    }

    private fun bindData() {
        binding.commissionField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowName)
        binding.interestField.bindTextTwoWayN(viewLifecycleOwner, viewModel.flowInterest)
    }

    private fun setListenersButtons() {
        binding.buttonSave.setOnClickListener {
            viewModel.saveCommission()
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