package com.example.testcode.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testcode.model.OpenData
import com.example.testcode.repository.network.OpenDataApi
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by dion on 2021/02/02.
 */
class OpenDataPagingSource : PagingSource<Int, OpenData>() {

    companion object {
        const val INDEX_SIZE = 30
        const val DEFAULT_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OpenData> {
        return try {
            val index = params.key ?: DEFAULT_INDEX
            val data = OpenDataApi.create().getOpenData(
                top = INDEX_SIZE,
                skip = index
            )

            LoadResult.Page(
                data = data,
                prevKey = if (index == DEFAULT_INDEX) null else index - INDEX_SIZE,
                nextKey = if (data.isEmpty()) null else index + INDEX_SIZE
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, OpenData>): Int {
        return DEFAULT_INDEX
    }
}