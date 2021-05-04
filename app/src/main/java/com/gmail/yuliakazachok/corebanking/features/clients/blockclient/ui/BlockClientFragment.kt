package com.gmail.yuliakazachok.corebanking.features.clients.blockclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gmail.yuliakazachok.corebanking.R
import com.gmail.yuliakazachok.corebanking.databinding.FragmentBlockclientBinding
import com.gmail.yuliakazachok.corebanking.features.clients.blockclient.presentation.BlockClientViewModel
import com.gmail.yuliakazachok.corebanking.libraries.utils.KeysArgsBundle
import com.gmail.yuliakazachok.corebanking.libraries.utils.closeKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BlockClientFragment : Fragment(), BlockClientViewModel.EventListener {

    @Inject
    lateinit var viewModel: BlockClientViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentBlockclientBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlockclientBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        viewModel.eventsDispatcher.bind(viewLifecycleOwner, this@BlockClientFragment)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonBlock.setOnClickListener {
            val number = arguments?.getLong(KeysArgsBundle.CLIENT_BLOCK)
            number?.let {
                viewModel.blockClient(number, binding.daysValue.text.toString().toInt())
                goToBack()
            }
        }
        binding.buttonCancel.setOnClickListener {
            goToBack()
        }
    }

    private fun goToBack() {
        closeKeyboard(binding)
        navController.popBackStack()
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