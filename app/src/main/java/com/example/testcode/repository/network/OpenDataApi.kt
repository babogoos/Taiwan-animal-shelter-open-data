package com.example.testcode.repository.network

import com.example.testcode.OpenData
import retrofit2.http.GET

/**
 * Created by dion on 2021/02/01.
 */
interface OpenDataApi {
    @GET("/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL")
    suspend fun getOpenData() : List<OpenData>

    companion object {
        fun create(): OpenDataApi = AppClientManager.retrofit.create(OpenDataApi::class.java)
    }
}