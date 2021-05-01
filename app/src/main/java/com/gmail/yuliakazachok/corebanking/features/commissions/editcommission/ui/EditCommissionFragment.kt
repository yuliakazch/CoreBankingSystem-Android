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
        setListenersButtons()
        return binding.root
    }

    private fun setListenersButtons() {
        binding.buttonSave.setOnClickListener {
            viewModel.saveCommission(
                binding.commissionField.text.toString(),
                binding.interestField.text.toString().toInt()
            )
            navController.popBackStack()
        }
        binding.buttonCancel.setOnClickListener {
            navController.popBackStack()
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