package com.example.testcode.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testcode.OpenData
import com.example.testcode.repository.datasource.OpenDataPagingSource
import com.example.testcode.repository.datasource.OpenDataPagingSource.Companion.INDEX_SIZE
import kotlinx.coroutines.flow.Flow

/**
 * Created by dion on 2021/02/01.
 */
interface IOpenDataRepository {
   suspend fun getAnimalPlaceList(): Flow<PagingData<OpenData>>
}

class OpenDataRepository : IOpenDataRepository {

   override suspend fun getAnimalPlaceList(): Flow<PagingData<OpenData>> {
      return Pager(
         PagingConfig(
            pageSize = INDEX_SIZE,
            enablePlaceholders = false
         )
      ) {
         OpenDataPagingSource()
      }.flow
   }
}
