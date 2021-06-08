package com.dicoding.capstoneproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddReportResponse(
	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable
