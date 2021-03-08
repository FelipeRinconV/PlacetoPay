package com.evertec.everteplacetopay.ui.generate_transaction

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.Const
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.data.DataController
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.databinding.FragmentGenerateTransactionBinding
import com.evertec.everteplacetopay.showToast
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import com.evertec.everteplacetopay.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenerateTransactionFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentGenerateTransactionBinding? = null


    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    //TODO hacer el procceso de base 64 manual para no tener que requerir android 0
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
                binding.txtSurname.text.toString(),
                binding.txtNumDocument.text.toString(),
                binding.txtEmail.text.toString(),
                binding.txtNumPhone.text.toString(),
                binding.txtNumCard.text.toString(),
                binding.txtExpiredMonth.text.toString(),
                binding.txtExpiredYear.text.toString(),
                binding.txtCvv.text.toString(),
                "COP",
                binding.txtAmount.text.toString(),
                binding.txtDescription.text.toString(),
                binding.txtReference.text.toString(),
                requireContext()
            )
        }


    }

    //TODO este obsver debe cambiarse ya que se mantiene activo despues del primer
    private fun setupObservers() {
        viewModel.generateTransaction().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.json.text = it.data.toString()

                    //TODO pasar el user creator
                    val newTransaction = TransactionEntity(
                        it.data.internalReference,
                        DataController.getUserId(),
                        it.data.reference,
                        it.data.lastDigits,
                        it.data.status.status,
                        it.data.amount.total.toDouble(),
                        it.data.status.date
                    )


                    if (newTransaction != null) {
                        viewModel.insertTransaction(newTransaction)
                        val bundle = Bundle()
                        bundle.putParcelable(Const.ITEM_TRANSACTION.name, it.data)
                        viewModel.removeObserverGenerateContract(this)
                        findNavController().navigate(R.id.resumeTransaction, bundle)
                    } else {
                        showToast("La transsaccion generada es nula", Toast.LENGTH_LONG)
                    }


                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    //binding.json.text = it.exception.message.toString()
                    showToast("${it.exception.message.toString()}", Toast.LENGTH_LONG)
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