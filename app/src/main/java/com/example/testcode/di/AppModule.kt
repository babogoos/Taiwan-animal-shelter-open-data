package com.example.testcode.di

import com.example.testcode.MainViewModel
import com.example.testcode.repository.IOpenDataRepository
import com.example.testcode.repository.OpenDataRepository
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

val dataModule = module {
    single<IOpenDataRepository> {
        OpenDataRepository()
    }
}