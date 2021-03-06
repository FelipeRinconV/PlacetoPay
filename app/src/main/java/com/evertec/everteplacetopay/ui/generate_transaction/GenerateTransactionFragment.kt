package com.evertec.everteplacetopay.ui.generate_transaction

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.room.Entity
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.databinding.FragmentGenerateTransactionBinding
import com.evertec.everteplacetopay.databinding.FragmentLoginBinding
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.output.PostJsonTransaction
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenerateTransactionFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentGenerateTransactionBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    //TODO hacer el procceso de base 64 manual para no tener que requerir andorid 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupUi()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupUi() {
        binding.btnIrResume.setOnClickListener {
            binding.json.text = viewModel.getNewPosJsonTransaction()
        }
    }

    private fun setupObservers() {

        viewModel.currentTransaction.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    findNavController().navigate(R.id.resumeTransaction)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error dowloading list", Toast.LENGTH_LONG)
                        .show()
                }
            }

        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGenerateTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}