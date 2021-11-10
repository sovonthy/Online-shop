package com.group6.onlineshop.view.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group6.onlineshop.OnlineShopApp
import com.group6.onlineshop.model.Profile
import com.group6.onlineshop.utils.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    val profile: MutableLiveData<Profile> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getProfile(authorize: String) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = OnlineShopApp.mainRepository.getProfile( authorize = authorize)) {
                    is ResultOf.Success -> {
                        profile.postValue(result.data)
                    }
                    is ResultOf.Error -> {
                        errorMessage.postValue(result.exception)
                    }
                }
            }

    }
}