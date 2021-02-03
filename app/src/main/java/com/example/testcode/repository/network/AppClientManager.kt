package com.example.testcode.repository.network

import android.util.Log
import com.example.testcode.util.OpenDataUtils.OPEN_DATA_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dion on 2021/02/01.
 */
object AppClientManager {
    val retrofit: Retrofit
    private var logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.i("interceptor msg", message)
        }
    })

    private var okHttpClient: OkHttpClient

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(logging).build()
        retrofit = Retrofit.Builder()
            .baseUrl(OPEN_DATA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}