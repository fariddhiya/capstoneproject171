package com.dicoding.capstoneproject.ui.detailreport

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
class DetailViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val reportDetail = MutableLiveData<Value>()

    fun getDetailReport(reportId: Int): MutableLiveData<Value> {
        viewModelScope.launch {
            when (val response = repository.getReportById(reportId)) {
                is Resource.Success -> {
                    response.data?.values.let {
                        reportDetail.postValue(it?.get(0))
                    }
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return reportDetail
    }

}