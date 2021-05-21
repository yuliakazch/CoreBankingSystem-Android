package com.gmail.yuliakazachok.corebanking.features.users.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentSigninBinding
import com.gmail.yuliakazachok.corebanking.features.users.signin.presentation.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment(), SignInViewModel.EventListener {

    @Inject
    lateinit var viewModel: SignInViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentSigninBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.signInButton.setOnClickListener {
            goToHome()
        }
    }

    private fun goToHome() {
        viewModel.auth(binding.loginField.text.toString(),binding.passwordField.text.toString())
        if (viewModel.checkTokenExist()) {
            navController.navigate(R.id.action_signInFragment_to_homeFragment)
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