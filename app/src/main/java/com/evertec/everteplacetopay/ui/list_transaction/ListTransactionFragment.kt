package com.evertec.everteplacetopay.ui.list_transaction

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.data.DataController
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.databinding.FragmentListTransactionBinding
import com.evertec.everteplacetopay.showToast
import com.evertec.everteplacetopay.singUpUser
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import com.evertec.everteplacetopay.vo.Resource
import com.example.awesomedialog.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListTransactionFragment : Fragment(), TransactionAdapter.OnTransactionListener {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentListTransactionBinding? = null
    private lateinit var transactionsAdapter: TransactionAdapter


    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transactionsAdapter = TransactionAdapter(requireContext(), this)

        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            confirmSignUp()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoGenerar.setOnClickListener {
            findNavController().navigate(R.id.generateTransactionFragment)
        }

        setupUi()
        setupObserver()
    }


    private fun setupUi() {

        binding.recyclerTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTransactions.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        binding.imgBack.setOnClickListener {
            confirmSignUp()
        }

        binding.recyclerTransactions.adapter = transactionsAdapter

    }

    private fun setupObserver() {
        viewModel.getListTransaction().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    //  showToast("Cargando lista", Toast.LENGTH_LONG)
                }
                is Resource.Success -> {

                    if (it.data.isEmpty()) {
                        binding.txtNoHaveTransaction.visibility = View.VISIBLE
                    } else {
                        binding.txtNoHaveTransaction.visibility = View.GONE
                        transactionsAdapter.setTransactionList(it.data)
                    }

                }
                is Resource.Failure -> {
                    Log.e("ERROR_LISTA", it.exception.message)
                    showToast("Error ${it.exception.message}", Toast.LENGTH_LONG)
                }
            }
        })


    }

    private fun confirmSignUp() {
        AwesomeDialog.build(requireActivity())
            .title(getString(R.string.alerta))
            .body(getString(R.string.cerrar_sesion_confirm))
            .onPositive(getString(R.string.cerrar_sesion_accept)) {
                singUpUser(requireActivity())
                findNavController().navigate(R.id.loginFragment)
            }
            .onNegative(getString(R.string.cancelar)) { }
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

    override fun onClickDeleteTransaction(transaction: TransactionEntity) {
        viewModel.deleteTransaction(transaction)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickUpdatedTransaction(transactionEntity: TransactionEntity) {

        viewModel.getStateTransactionNow(viewModel.transactionEntityToGatewayQuery(transactionEntity))
            .observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is Resource.Loading -> {
                            showToast("Actualizando item", Toast.LENGTH_LONG)
                        }
                        is Resource.Success -> {
                            viewModel.updateTransaction(it.data, transactionEntity)
                            showToast("Item actualizado", Toast.LENGTH_LONG)
                        }
                        is Resource.Failure -> {
                            showToast(
                                "Error actualizando item${it.exception.message}",
                                Toast.LENGTH_LONG
                            )
                        }
                    }
                })


    }

}