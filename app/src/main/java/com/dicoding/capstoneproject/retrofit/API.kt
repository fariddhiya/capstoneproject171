package com.dicoding.capstoneproject.retrofit

import com.dicoding.capstoneproject.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

interface API {
    @Multipart
    @POST("upload_image_api")
    fun uploadImage(
        @Part part: MultipartBody.Part
    ): Call<ImageResponse>

    @POST("predict_report")
    fun uploadReport(
        @Body req: ReportEntity
    ): Call<ReportResp>

    @POST("add_report")
    fun addReport(
        @Body req: ReportEntity
    ): Call<AddReportResponse>

    @PUT("{id}/update")
    fun putReport(
        @Path("id") id: Int
    ): Call<AddReportResponse>
}