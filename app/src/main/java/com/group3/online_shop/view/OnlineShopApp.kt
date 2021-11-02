package com.group3.online_shop.view

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate


class OnlineShopApp : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
//
//        @SuppressLint("StaticFieldLeak")
//        lateinit var shopRepository: ShopRepository
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
//        shopRepository = ShopRepository(context = this, appService = appService)
    }

}