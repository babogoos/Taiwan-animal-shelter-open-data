package com.example.testcode.di

import com.example.testcode.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by dion on 2021/02/01.
 */

val appModule = module {
    viewModel {
        MainViewModel()
    }
}