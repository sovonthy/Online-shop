package com.group3.online_shop

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.epoxy.data.http.appService
import com.group3.online_shop.repository.MainRepository

class OnlineShopApp : Application() {

    companion object {
        lateinit var appContext: Context
            private set

        @SuppressLint("StaticFieldLeak")
        lateinit var mainRepository: MainRepository
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        mainRepository = MainRepository(context = this, appService = appService)
    }

}