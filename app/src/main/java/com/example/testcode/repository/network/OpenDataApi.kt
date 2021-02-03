package com.example.testcode.repository.network

import com.example.testcode.model.OpenData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dion on 2021/02/01.
 */
interface OpenDataApi {
    @GET("/Service/OpenData/TransService.aspx")
    suspend fun getOpenData(
        @Query("UnitId") unitId: String = "QcbUEzN6E6DL",
        @Query("\$top") top: Int,
        @Query("\$skip") skip: Int
    ): List<OpenData>

    companion object {
        fun create(): OpenDataApi = AppClientManager.retrofit.create(OpenDataApi::class.java)
    }
}