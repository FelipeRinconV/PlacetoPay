package com.evertec.everteplacetopay.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.evertec.everteplacetopay.*
import com.evertec.everteplacetopay.data.DataController
import com.evertec.everteplacetopay.data.DataController.userData
import com.evertec.everteplacetopay.databinding.FragmentLoginBinding
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import com.evertec.everteplacetopay.vo.Resource
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    /**
     * Setup ui
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameLayout: TextInputEditText = binding.txtName
        val passwordLayout: TextInputEditText = binding.txtPassword

        DataController.userData = getUserSfp(requireActivity())

        if (DataController.userData != null) {
            DataController.setData(DataController.userData!!)
            findNavController().navigate(R.id.listTransactionFragment)
        } else {
            binding.containedButton.setOnClickListener {

                val name: String = nameLayout.text.toString()
                val password: String = passwordLayout.text.toString()

                viewModel.getUser(name, password)
                    .observe(viewLifecycleOwner, Observer {
                        when (it) {
                            is Resource.Loading -> {
                                binding.progressBar2.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                binding.progressBar2.visibility = View.GONE
                                val userEntityInput = it.data

                                if (userEntityInput != null) {
                                    DataController.setData(userEntityInput)
                                    saveUserSfp(requireActivity(), userEntityInput)
                                    findNavController().navigate(R.id.listTransactionFragment)
                                } else {
                                    AwesomeDialog.build(requireActivity())
                                        .title(getString(R.string.nuevo_usuario))
                                        .body(getString(R.string.por_favor_registrese_primer))
                                        .onPositive(getString(R.string.Close)) {
                                        }
                                }
                            }
                            is Resource.Failure -> {
                                binding.progressBar2.visibility = View.GONE
                                AwesomeDialog.build(requireActivity())
                                    .title(getString(R.string.error))
                                    .body(getString(R.string.ocurrio_un_error_login))
                                    .onPositive(getString(R.string.aceptar)) {
                                    }
                            }
                        }
                    })
            }

            binding.btnRegister.setOnClickListener {

                val name: String = nameLayout.text.toString()
                val password: String = passwordLayout.text.toString()
                viewModel.saveUser(name, password)

                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.registro_tittle))
                    .body(getString(R.string.your_new_account_have_been_created))
                    .onPositive(getString(R.string.aceptar)) {
                        findNavController().navigate(R.id.listTransactionFragment)
                    }
            }

        }

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