package com.example.epoxy.data.http

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.group3.online_shop.utils.Constant
import com.group3.online_shop.OnlineShopApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val httpClient = OkHttpClient
    .Builder()
    .addInterceptor(ChuckerInterceptor.Builder(OnlineShopApp.appContext).build())
    .build()

private val retrofit = Retrofit
    .Builder()
    .client(httpClient)
    .baseUrl(Constant.Url.url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val appService: AppService = retrofit.create()