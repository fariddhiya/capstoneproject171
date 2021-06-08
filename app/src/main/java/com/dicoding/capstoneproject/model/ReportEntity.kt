package com.dicoding.capstoneproject.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReportEntity(
    @SerializedName("category")
    @Expose
    var category: String? = "",

    @SerializedName("subcategory")
    @Expose
    var subcategory: String? = "",

    @SerializedName("description")
    @Expose
    var description: String? = "",

    @SerializedName("latitude")
    @Expose
    var latitude: Double? = 0.0,

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = 0.0,

    @SerializedName("img_url")
    @Expose
    var img_url: String? = "",
): Parcelable