package com.example.testcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import androidx.lifecycle.Observer
import com.google.gson.Gson
import okhttp3.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        viewModel.itemList.observe(this, Observer {
            this@MainActivity.runOnUiThread {
                mRecyclerView.adapter = MainAdapter(it, applicationContext)
            }
        })
        viewModel.loadOpenData()
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
}
