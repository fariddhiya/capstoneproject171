package com.dicoding.capstoneproject.ui.report

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
class ReportViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val listReport = MutableLiveData<ArrayList<Value>>()

    fun getListReport(): MutableLiveData<ArrayList<Value>> {
        viewModelScope.launch {
            val report: ArrayList<Value> = arrayListOf()
            when (val response = repository.getReports()) {
                is Resource.Success -> {
                    response.data?.values?.forEach {
                        report.add(it)
                    }
                    listReport.postValue(report)
                }
                is Resource.Error -> {
                    response.message
                }
            }
        }
        return listReport
    }
}