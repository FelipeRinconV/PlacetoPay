package com.evertec.everteplacetopay.ui.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.evertec.everteplacetopay.data.repository.Repository

class LoginViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {


    fun printText() {

    }

}