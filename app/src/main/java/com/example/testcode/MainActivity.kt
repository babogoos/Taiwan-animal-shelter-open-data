package com.example.testcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        connectOpenData()
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun connectOpenData() {
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
                    this@MainActivity.runOnUiThread {
                        mRecyclerView.adapter = MainAdapter(openDataChooseList, applicationContext)
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
