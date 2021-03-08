package com.evertec.everteplacetopay.ui.resume_transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.Const
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionInput
import com.evertec.everteplacetopay.databinding.FragmentResumeTransactionBinding
import com.evertec.everteplacetopay.getState


class ResumeTransaction : Fragment() {

    private lateinit var transaction: ProcessTransactionInput;

    private var _binding: FragmentResumeTransactionBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            //TODO DAR AVISO CON UN DIALOGO
            findNavController().navigate(R.id.listTransactionFragment)
        }
        setupUi()
    }

    private fun setupUi() {

        binding.txtDate.text = transaction.date.subSequence(0, 10)
        binding.txtStatus.text = getState(transaction.status.status)
        binding.txtAmountOriginal.text = transaction.amount.total.toString()
        binding.txtInterests.text = "0"
        binding.txtTotalPayed.text = transaction.amount.total.toString()
        binding.txtBank.text = transaction.issuerName
        binding.txtCus.text = transaction.authorization
        binding.txtReceipt.text = transaction.receipt
        binding.txtStatusSub.text = transaction.status.status

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            transaction = it.getParcelable<ProcessTransactionInput>(Const.ITEM_TRANSACTION.name)!!
        }

        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.listTransactionFragment)
        }
    }

}