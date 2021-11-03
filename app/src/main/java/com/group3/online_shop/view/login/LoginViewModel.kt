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

    private val login: MutableLiveData<Login> = MutableLiveData()

    fun login(email: String, password: String) {

        val param = hashMapOf("email" to email, "password" to password)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = OnlineShopApp.mainRepository.login( param = param)) {
                is ResultOf.Success -> {
                    result.data
                    println("data ${result.data}")
//                    login.postValue(result.data)
                }
                is ResultOf.Error -> {

                }
            }
        }

    }


}