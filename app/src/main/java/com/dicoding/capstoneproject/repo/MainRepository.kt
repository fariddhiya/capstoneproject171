package com.dicoding.capstoneproject.repo

import com.dicoding.capstoneproject.data.ApiService
import com.dicoding.capstoneproject.data.ReportResponse
import com.dicoding.capstoneproject.utils.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getReports(): Resource<ReportResponse> {
        apiService.getReports().let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getRoad(): Resource<ReportResponse> {
        apiService.getCategoryRoad().let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getTree(): Resource<ReportResponse>{
        apiService.getCategoryTree().let { response ->
            if (response.isSuccessful){
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getGarbage(): Resource<ReportResponse> {
        apiService.getCategoryGarbage().let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getReportById(reportId: Int): Resource<ReportResponse>{
        apiService.getReportById(reportId).let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }
}
