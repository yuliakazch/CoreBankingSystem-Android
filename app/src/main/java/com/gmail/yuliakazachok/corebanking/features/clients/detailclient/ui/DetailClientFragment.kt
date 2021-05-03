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
import com.gmail.yuliakazachok.corebanking.shared.clients.domain.entities.ClientStates
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
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getClient(arguments?.getLong(KeysArgsBundle.CLIENT_DETAIL))
        setText()
    }

    private fun setText() {
        viewModel.client.onEach {
            with(binding) {
                fioValue.text = it.fio
                passportValue.text = it.numberPassport.toString()
                dateBirthValue.text = SimpleDateFormat("dd.MM.yyyy", Locale("Rus")).format(it.dateBirth)
                placeValue.text = it.place
                stateValue.text = when (viewModel.getStateClient(it.isCredit, it.isTariff, it.countBlockDays)) {
                    ClientStates.STATE_NOT_TARIFF -> resources.getString(R.string.not_tariff)
                    ClientStates.STATE_NOT_CREDIT -> resources.getString(R.string.not_credit)
                    ClientStates.STATE_YES_CREDIT -> resources.getString(R.string.yes_credit)
                    else -> resources.getString(R.string.locked)
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