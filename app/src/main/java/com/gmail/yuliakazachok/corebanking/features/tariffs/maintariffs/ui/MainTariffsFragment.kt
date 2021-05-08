package com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentMaintariffsBinding
import com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.presentation.MainTariffsViewModel
import com.gmail.yuliakazachok.corebanking.features.tariffs.maintariffs.ui.adapter.TariffAdapter
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainTariffsFragment : Fragment(), MainTariffsViewModel.EventListener {

    @Inject
    lateinit var viewModel: MainTariffsViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentMaintariffsBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaintariffsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@MainTariffsFragment)
        viewModel.numberPassport.value = arguments?.getLong(KeysArgsBundle.TARIFF_CLIENT)
        setAdapter()
        setMode()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun setMode() {
        viewModel.mode.value = arguments?.getString(KeysArgsBundle.TARIFF_MODE)
        if (viewModel.mode.value == KeysArgsBundle.TARIFF_MODE_CLIENT) {
            setModeClient()
        } else {
            setModeAll()
        }
    }

    private fun setModeAll() {
        with(binding) {
            tariffsClientText.isVisible = false
            giveTariffText.isVisible = false
            tariffsRadioGroup.isVisible = false
            giveTariffButton.isVisible = false
            swipeRefresh.setOnRefreshListener {
                viewModel.getTariffs()
                binding.swipeRefresh.isRefreshing = false
            }
            addButton.setOnClickListener {
                goToCreateTariff()
            }
        }
    }

    private fun setModeClient() {
        with(binding) {
            addButton.isVisible = false
            swipeRefresh.setOnRefreshListener {
                binding.swipeRefresh.isRefreshing = false
            }
            viewModel.listTariffsNotClient.onEach { list ->
                this@MainTariffsFragment.context?.let { context ->
                    list.forEach {
                        val radioButton = RadioButton(context)
                        radioButton.text = it.name
                        radioButton.id = it.id
                        tariffsRadioGroup.addView(radioButton)
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
            giveTariffButton.setOnClickListener {
                viewModel.saveAvailableTariff(tariffsRadioGroup.checkedRadioButtonId)
                navController.popBackStack()
            }
            viewModel.listTariff.onEach {
                if (viewModel.isEmptyListTariffs()) {
                    tariffsClientText.isVisible = false
                }
            }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
        }
    }

    private fun setAdapter() {
        val adapter = TariffAdapter(viewModel)
        binding.listTariffs.adapter = adapter
        viewModel.listTariff.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    private fun getData() {
        if (viewModel.mode.value == KeysArgsBundle.TARIFF_MODE_CLIENT) {
            viewModel.getClientTariffs()
        } else {
            viewModel.getTariffs()
        }
    }

    private fun goToCreateTariff() {
        navController.navigate(R.id.action_mainTariffsFragment_to_editTariffsFragment)
    }

    override fun goToDetailTariff(idTariff: Int) {
        navController.navigate(
            R.id.action_mainTariffsFragment_to_detailTariffsFragment,
            Bundle().apply {
                putInt(KeysArgsBundle.TARIFF_DETAIL, idTariff)
            }
        )
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