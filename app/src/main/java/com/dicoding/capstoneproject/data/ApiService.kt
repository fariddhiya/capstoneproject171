package com.dicoding.capstoneproject.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("get")
    suspend fun getReports(): Response<ReportResponse>

    @GET("jalan")
    suspend fun getCategoryRoad(): Response<ReportResponse>

    @GET("pohon")
    suspend fun getCategoryTree(): Response<ReportResponse>

    @GET("sampah")
    suspend fun getCategoryGarbage(): Response<ReportResponse>

    @GET("get/{id_laporan}")
    suspend fun getReportById(
        @Path("id_laporan") reportId: Int): Response<ReportResponse>

//    @GET("get/:id_laporan")
//    suspend fun getReportById(): Response<ReportResponse>
}