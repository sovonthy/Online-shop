package com.group3.online_shop.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group3.online_shop.OnlineShopApp
import com.group3.online_shop.model.Login
import com.group3.online_shop.utils.ResultOf
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