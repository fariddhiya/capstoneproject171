package com.dicoding.capstoneproject.ui.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.repo.MainRepository
import com.dicoding.capstoneproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(val repository: MainRepository) : ViewModel() {

    private val listRoadReports = MutableLiveData<ArrayList<Value>>()
    val listTreeReports = MutableLiveData<ArrayList<Value>>()
    val listGarbageReports = MutableLiveData<ArrayList<Value>>()


    fun getRoad(): MutableLiveData<ArrayList<Value>> {
        viewModelScope.launch {
            val reportResponse: ArrayList<Value> = arrayListOf()
            when (val response = repository.getRoad()) {
                is Resource.Success -> {
                    response.data?.values?.forEach {
                        reportResponse.add(it)
                    }
                    listRoadReports.postValue(reportResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listRoadReports
    }

    fun getTree(): MutableLiveData<ArrayList<Value>> {
        viewModelScope.launch {
            val reportResponse: ArrayList<Value> = arrayListOf()
            when (val response = repository.getTree()) {
                is Resource.Success -> {
                    response.data?.values?.forEach {
                        reportResponse.add(it)
                    }
                    listTreeReports.postValue(reportResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listTreeReports
    }

    fun getGarbage(): MutableLiveData<ArrayList<Value>>{
        viewModelScope.launch {
            val reportResponse: ArrayList<Value> = arrayListOf()
            when (val response = repository.getGarbage()) {
                is Resource.Success -> {
                    response.data?.values?.forEach {
                        reportResponse.add(it)
                    }
                    listGarbageReports.postValue(reportResponse)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listGarbageReports
    }

}
