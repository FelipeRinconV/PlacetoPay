package com.evertec.everteplacetopay.ui.resume_transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.databinding.FragmentLoginBinding
import com.evertec.everteplacetopay.databinding.FragmentResumeTransactionBinding


class ResumeTransaction : Fragment() {


    private var _binding: FragmentResumeTransactionBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.listTransactionFragment)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResumeTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}