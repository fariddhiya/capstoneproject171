package com.dicoding.capstoneproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReportRequest {
    @SerializedName("id_laporan")
    @Expose
    var id_laporan: Int? = 0

    @SerializedName("kategori")
    @Expose
    var kategori: String? = null

    @SerializedName("sub_kategori")
    @Expose
    var sub_kategori: String? = null

    @SerializedName("deskripsi")
    @Expose
    var deskripsi: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: Double? = 0.0

    @SerializedName("longitude")
    @Expose
    var longitude: Double = 0.0

    @SerializedName("id_kecamatan")
    @Expose
    var id_kecamatan: Int = 0

    @SerializedName("createdate")
    @Expose
    var createdate: String? = null

}