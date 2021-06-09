package com.dicoding.capstoneproject.repo.repointer

import com.dicoding.capstoneproject.data.ReportResponse
import com.dicoding.capstoneproject.utils.Resource

interface Repository {
    suspend fun getRoadReports(): Resource<ReportResponse>
    suspend fun getTreeReports():Resource<ReportResponse>
    suspend fun getGarbageReports():Resource<ReportResponse>
}