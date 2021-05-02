package com.gmail.yuliakazachok.corebanking.features.tariffs.detailtariff.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentDetailtariffBinding
import com.gmail.yuliakazachok.corebanking.features.tariffs.detailtariff.presentation.DetailTariffViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.tariffs.domain.entities.Tariff
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DetailTariffFragment : Fragment(), DetailTariffViewModel.EventListener {

    @Inject
    lateinit var viewModel: DetailTariffViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentDetailtariffBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailtariffBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        setListenersButtons()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getTariffById(arguments?.getInt(KeysArgsBundle.TARIFF_DETAIL))
        setText()
    }

    private fun setListenersButtons() {
        binding.buttonEdit.setOnClickListener {
            goToEditTariffs(viewModel.getTariff())
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.deleteTariff()
            goToMainTariffs()
        }
    }

    private fun setText() {
        viewModel.tariff.onEach {
            binding.nameValue.text = it.name
            binding.rateValue.text = it.rate.toString()
            binding.minSumValue.text = it.minSum.toString()
            binding.maxSumValue.text = it.maxSum.toString()
            binding.minTermValue.text = it.minTerm.toString()
            binding.maxTermValue.text = it.maxTerm.toString()
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun goToMainTariffs() {
        navController.popBackStack()
    }

    private fun goToEditTariffs(tariff: Tariff?) {
        tariff?.let {
            navController.navigate(
                R.id.action_detailTariffsFragment_to_editTariffsFragment,
                Bundle().apply {
                    putSerializable(KeysArgsBundle.TARIFF_EDIT, it)
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