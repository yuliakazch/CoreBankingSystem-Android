package com.gmail.yuliakazachok.corebanking.features.clients.detailclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.databinding.FragmentDetailclientBinding
import com.gmail.yuliakazachok.corebanking.features.tariffs.detailtariff.presentation.DetailTariffViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailClientFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailTariffViewModel

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}