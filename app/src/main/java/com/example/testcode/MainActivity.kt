package com.example.testcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var list: RecyclerView
    private lateinit var adapter: OpenDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initAdapter()
    }

    private fun initView() {
        list = findViewById(R.id.recyclerView)
        list.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        list.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initAdapter() {
        adapter = OpenDataAdapter()
        list.adapter = adapter
        lifecycleScope.launch {
            viewModel.getData().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
