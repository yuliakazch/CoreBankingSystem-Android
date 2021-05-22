package com.gmail.yuliakazachok.corebanking.features.credits.listcredits.ui

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
import com.gmail.yuliakazachok.corebanking.databinding.FragmentListcreditsBinding
import com.gmail.yuliakazachok.corebanking.features.credits.listcredits.presentation.ListCreditsViewModel
import com.gmail.yuliakazachok.corebanking.features.credits.listcredits.ui.adapter.CreditAdapter
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ListCreditsFragment : Fragment(), ListCreditsViewModel.EventListener {

    @Inject
    lateinit var viewModel: ListCreditsViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentListcreditsBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListcreditsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@ListCreditsFragment)
        setAdapter()
        setListeners()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getHistoryCredits(arguments?.getLong(KeysArgsBundle.CREDIT_LIST))
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getHistoryCredits(arguments?.getLong(KeysArgsBundle.CREDIT_LIST))
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setAdapter() {
        val adapter = CreditAdapter(viewModel)
        binding.listCredits.adapter = adapter
        viewModel.listCredits.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycle.coroutineScope)
    }

    override fun goToDetailCredit(id: Int) {
        navController.navigate(
            R.id.action_listCreditsFragment_to_detailCreditFragment,
            Bundle().apply {
                putInt(KeysArgsBundle.CREDIT_DETAIL_ID, id)
                putString(KeysArgsBundle.CREDIT_MODE, KeysArgsBundle.CREDIT_MODE_ID)
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