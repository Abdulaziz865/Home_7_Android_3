package com.example.home_7_android_3.hilt.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.home_7_android_3.hilt.data.models.HomeModel
import com.example.home_7_android_3.hilt.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _homeModelLiveData = MutableLiveData<List<HomeModel>>()
    val homeModelLiveData: LiveData<List<HomeModel>> = _homeModelLiveData

    private val _errorItemLiveData = MutableLiveData<String>()
    val errorItemLiveData: LiveData<String> = _errorItemLiveData

    var localItemLiveData: LiveData<List<HomeModel>>? = null

    init {
        getPhoto()
        getLocalPhoto()
    }

    private fun getPhoto() {
        return repository.getPhoto(onSuccess = {
            _homeModelLiveData.value = it
        }, onFailure = {
            _errorItemLiveData.value = it
        })
    }

    private fun getLocalPhoto() {
        localItemLiveData = repository.getLocalItems()
    }
}