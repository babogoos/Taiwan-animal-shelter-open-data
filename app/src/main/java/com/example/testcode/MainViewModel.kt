package com.example.testcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by dion on 2021/02/01.
 */
class MainViewModel : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    private val _itemList: MutableLiveData<List<OpenData>> = MutableLiveData()
    val itemList: LiveData<List<OpenData>> = _itemList

    fun connectOpenData() {
        try {
            val request = Request.Builder()
                .url(OpenDataUtils.OPENDATA_URL)
                .build()
            val client = OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(100000, TimeUnit.MILLISECONDS)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d(TAG, e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val resStr = response.body?.string()
                    val openDataList: List<OpenData> =
                        Gson().fromJson(resStr, Array<OpenData>::class.java).toList()
                    val openDataChooseList = mutableListOf<OpenData>()
                    for (i in 0 until 20) {
                        openDataChooseList.add(openDataList[i])
                    }
                    Log.d(TAG, "onResponse: openDataChooseList = $openDataChooseList")

                    _itemList.postValue(openDataChooseList)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}