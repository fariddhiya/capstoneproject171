package com.dicoding.capstoneproject.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageResponse(
    @SerializedName("status_code")
    @Expose
    var status_code: Int = 0,
    @SerializedName("body")
    @Expose
    var bodyResponse: BodyResponse,
) : Parcelable

@Parcelize
data class BodyResponse(
    @SerializedName("filepath")
    @Expose
    var filepath: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null
) : Parcelable