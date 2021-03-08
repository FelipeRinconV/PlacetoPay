package com.evertec.everteplacetopay.ui.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.evertec.everteplacetopay.data.model.UserEntity
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.ui.login.ui.login.LoginFormState
import com.evertec.everteplacetopay.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    var mUserEntity = UserEntity("", "")


    fun getUser(name: String, password: String) = liveData<Resource<UserEntity>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emitSource(repository.getUser(name, password).map { Resource.Success(it) })
        } catch (ex: Exception) {
            emit(Resource.Failure(ex))
        }
    }

    fun saveUser(name: String, password: String) = viewModelScope.launch {
        val userEntity = UserEntity(name, password)
        mUserEntity.userId = repository.insertUser(userEntity)
    }


}