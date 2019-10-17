package com.shiho.base.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @ClassName: NetworkHelper
 * @Description: MvRx test nework accesser.
 * @Date: 2019-09-18 14:18
 * @History:
 * <date> <version> <desc>
 */
class NetworkHelper(serviceUrl: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(serviceUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val apiService: NetworkService

    init {
        apiService = retrofit.create(NetworkService::class.java)
    }

}