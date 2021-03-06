package com.evertec.everteplacetopay.ui.generate_transaction

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.databinding.FragmentGenerateTransactionBinding
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

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
        setupUi()
        setupObservers()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupUi() {
        //Event to generates pay
        binding.btnIrResume.setOnClickListener {
            viewModel.getNewPosJsonTransaction(
                binding.txtNamePayer.text.toString(),
                binding.txtSurname.toString(),
                binding.txtNumDocument.toString(),
                binding.txtEmail.toString(),
                binding.txtNumPhone.toString(),
                binding.txtNumCard.toString(),
                binding.txtExpiredMonth.toString(),
                binding.txtExpiredYear.toString(),
                binding.txtCvv.toString(),
                "COP",
                binding.txtAmount.toString(),
                binding.txtDescription.toString(),
                binding.txtReference.toString(),
                requireContext()
            )
        }
    }

    private fun setupObservers() {

        viewModel.currentTransaction.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.json.text = it.data.toString()
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    it.exception.printStackTrace()
                    binding.json.text = it.exception.message.toString()
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