package com.gmail.yuliakazachok.corebanking.features.clients.detailclient.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentDetailclientBinding
import com.gmail.yuliakazachok.corebanking.features.clients.detailclient.presentation.DetailClientViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailClientFragment : Fragment(), DetailClientViewModel.EventListener {

    @Inject
    lateinit var viewModel: DetailClientViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentDetailclientBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailclientBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@DetailClientFragment)
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getClient(arguments?.getLong(KeysArgsBundle.CLIENT_DETAIL))
        setTextAndButtons()
    }

    private fun setListeners() {
        with(binding) {
            blockButton.setOnClickListener {
                navController.navigate(
                    R.id.action_detailClientFragment_to_blockClientFragment,
                    Bundle().apply {
                        putLong(KeysArgsBundle.CLIENT_BLOCK, viewModel.getNumberPassport())
                    }
                )
            }
            tariffsButton.setOnClickListener {
                navController.navigate(
                    R.id.action_detailClientFragment_to_mainTariffsFragment,
                    Bundle().apply {
                        putString(KeysArgsBundle.TARIFF_MODE, KeysArgsBundle.TARIFF_MODE_CLIENT)
                        putLong(KeysArgsBundle.TARIFF_CLIENT, viewModel.getNumberPassport())
                    }
                )
            }
            historyCreditsButton.setOnClickListener {
                navController.navigate(
                    R.id.action_detailClientFragment_to_listCreditsFragment,
                    Bundle().apply {
                        putLong(KeysArgsBundle.CREDIT_LIST, viewModel.getNumberPassport())
                    }
                )
            }
            deleteButton.setOnClickListener {
                viewModel.deleteClient()
                navController.popBackStack()
            }
        }
    }

    private fun setTextAndButtons() {
        viewModel.client.onEach {
            with(binding) {
                fioValue.text = it.fio
                passportValue.text = it.numberPassport.toString()
                dateBirthValue.text =
                    SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(it.dateBirth)
                placeValue.text = it.place
                stateValue.text = when (it.state) {
                    ClientState.STATE_NOT_TARIFF -> resources.getString(R.string.not_tariff)
                    ClientState.STATE_NOT_CREDIT -> resources.getString(R.string.not_credit)
                    ClientState.STATE_YES_CREDIT -> resources.getString(R.string.yes_credit)
                    else -> resources.getString(R.string.locked) + " на " + it.countBlockDays + " дней"
                }
                creditButton.isEnabled = it.state != ClientState.STATE_NOT_TARIFF
                creditButton.text =
                    if (it.state == ClientState.STATE_NOT_TARIFF || it.state == ClientState.STATE_NOT_CREDIT) {
                        resources.getString(R.string.give_credit)
                    } else {
                        resources.getString(R.string.active_credit)
                    }
                blockButton.isEnabled = it.state == ClientState.STATE_YES_CREDIT

                creditButton.setOnClickListener { _ ->
                    if (it.state == ClientState.STATE_NOT_CREDIT) {
                        navController.navigate(
                            R.id.action_detailClientFragment_to_editCreditFragment,
                            Bundle().apply {
                                putLong(KeysArgsBundle.CREDIT_EDIT, viewModel.getNumberPassport())
                            }
                        )
                    } else {
                        navController.navigate(
                            R.id.action_detailClientFragment_to_detailCreditFragment,
                            Bundle().apply {
                                putLong(
                                    KeysArgsBundle.CREDIT_DETAIL_PASSPORT,
                                    viewModel.getNumberPassport()
                                )
                                putString(KeysArgsBundle.CREDIT_MODE, KeysArgsBundle.CREDIT_MODE_PASSPORT)
                            }
                        )
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
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