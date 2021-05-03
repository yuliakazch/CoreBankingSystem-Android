package com.gmail.yuliakazachok.corebanking.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentHomeBinding
import com.gmail.yuliakazachok.corebanking.features.home.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.tariffs.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_mainTariffsFragment)
        }
        binding.credits.setOnClickListener {
            // TODO: переход на кредиты
        }
        binding.clients.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_filtersClientsFragment)
        }
        binding.commissions.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_mainCommissionsFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}