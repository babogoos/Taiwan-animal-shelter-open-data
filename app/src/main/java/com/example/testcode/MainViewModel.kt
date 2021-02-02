package com.example.testcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcode.repository.IOpenDataRepository
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


/**
 * Created by dion on 2021/02/01.
 */
class MainViewModel : ViewModel() {
    private val _itemList: MutableLiveData<List<OpenData>> = MutableLiveData()
    val itemList: LiveData<List<OpenData>> = _itemList

    private val openDataRepository: IOpenDataRepository by inject(IOpenDataRepository::class.java)

    fun loadOpenData() {
        viewModelScope.launch {
            _itemList.postValue(openDataRepository.getAnimalPlaceList())
        }
    }
}