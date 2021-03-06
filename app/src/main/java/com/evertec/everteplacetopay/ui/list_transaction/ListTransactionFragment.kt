package com.evertec.everteplacetopay.ui.list_transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.databinding.FragmentGenerateTransactionBinding
import com.evertec.everteplacetopay.databinding.FragmentListTransactionBinding
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.vo.Resource


class ListTransactionFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentListTransactionBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoGenerar.setOnClickListener {
            findNavController().navigate(R.id.generateTransactionFragment)
        }
    }

    private fun setupObserver() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}