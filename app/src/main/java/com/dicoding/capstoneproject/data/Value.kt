package com.dicoding.capstoneproject.data


import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Value(
    val createdat: String,
    val deskripsi: String,
    val foto: String,
    @SerializedName("id_kecamatan")
    val idKecamatan: String,
    @SerializedName("id_laporan")
    val idLaporan: Int,
    val kategori: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("sub_kategori")
    val subKategori: String,
    val vote: Int
) : Parcelable