package com.skw.network

import com.skw.constants.AppConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient
{
    val requestInterface = Retrofit.Builder().baseUrl(AppConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    fun getClient(): Retrofit?
    {
        return requestInterface
    }
}