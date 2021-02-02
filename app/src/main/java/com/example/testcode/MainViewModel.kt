package com.example.testcode

import androidx.lifecycle.*
import com.example.testcode.repository.IOpenDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.java.KoinJavaComponent.inject


/**
 * Created by dion on 2021/02/01.
 */
class MainViewModel : ViewModel() {
    private val openDataRepository: IOpenDataRepository by inject(IOpenDataRepository::class.java)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    suspend fun getData() = openDataRepository.getAnimalPlaceList()
}