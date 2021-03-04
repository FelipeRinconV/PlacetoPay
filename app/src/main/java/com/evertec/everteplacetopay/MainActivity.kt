package com.evertec.everteplacetopay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.evertec.everteplacetopay.databinding.LoginActivityBinding
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi(binding);
    }


    private fun initUi(binding: LoginActivityBinding) {
        binding.button.setOnClickListener {

            binding.textView.text = viewModel.printText()

        }
    }
}