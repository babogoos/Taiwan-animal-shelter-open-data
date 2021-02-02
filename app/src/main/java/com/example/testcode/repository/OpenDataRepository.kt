package com.example.testcode.repository

import com.example.testcode.OpenData
import com.example.testcode.repository.network.OpenDataApi

/**
 * Created by dion on 2021/02/01.
 */
interface IOpenDataRepository {
   suspend fun getAnimalPlaceList(): List<OpenData>
}

class OpenDataRepository : IOpenDataRepository {
   override suspend fun getAnimalPlaceList(): List<OpenData> {
      return OpenDataApi.create().getOpenData()
   }
}



