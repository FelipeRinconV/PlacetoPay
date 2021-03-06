package com.evertec.everteplacetopay.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.databinding.FragmentLoginBinding
import com.evertec.everteplacetopay.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null


    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    /**
     * Setup ui
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containedButton.setOnClickListener {
            findNavController().navigate(R.id.listTransactionFragment)
        }

        binding.containedButton.text = viewModel.printText()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}