package com.gmail.yuliakazachok.corebanking.features.commissions.detailcommission.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentDetailcommissionBinding
import com.gmail.yuliakazachok.corebanking.features.commissions.detailcommission.presentation.DetailCommissionViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.commissions.domain.entities.Commission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DetailCommissionFragment : Fragment(), DetailCommissionViewModel.EventListener {

    @Inject
    lateinit var viewModel: DetailCommissionViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentDetailcommissionBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailcommissionBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.commission.value =
            arguments?.getSerializable(KeysArgsBundle.COMMISSION_DETAIL) as? Commission
        setListenersButtons()
        setText()
        return binding.root
    }

    private fun setListenersButtons() {
        binding.buttonEdit.setOnClickListener {
            goToEditCommissions()
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.deleteCommission()
            goToMainCommissions()
        }
    }

    private fun setText() {
        viewModel.commission.onEach {
            it?.let {
                binding.nameValue.text = it.name
                binding.interestValue.text = it.interest.toString()
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun goToMainCommissions() {
        navController.popBackStack()
    }

    private fun goToEditCommissions() {
        // TODO: переход на экран редактирования комиссии
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