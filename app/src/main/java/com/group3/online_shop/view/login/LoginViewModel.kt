package com.group3.online_shop.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group3.online_shop.OnlineShopApp
import com.group3.online_shop.model.User
import com.group3.online_shop.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val user: MutableLiveData<User> = MutableLiveData()

    fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = OnlineShopApp.mainRepository.login()) {
                is ResultOf.Success -> {
//                    user.postValue(response.data)
                }

                is ResultOf.Error -> {
                    println("Error .... ${response.exception}")
                }
            }
        }
    }


}