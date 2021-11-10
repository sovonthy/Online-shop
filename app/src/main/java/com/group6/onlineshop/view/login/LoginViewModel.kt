package com.group6.onlineshop.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group6.onlineshop.OnlineShopApp
import com.group6.onlineshop.model.Login
import com.group6.onlineshop.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val login: MutableLiveData<Login> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun login(email: String, password: String) {

        val param = hashMapOf("email" to email, "password" to password)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = OnlineShopApp.mainRepository.login( param = param)) {
                is ResultOf.Success -> {
                    login.postValue(result.data)
                }
                is ResultOf.Error -> {
                    errorMessage.postValue(result.exception)
                }
            }
        }

    }


}