package com.dicoding.capstoneproject.fav

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    //    @SerializedName("id_laporan")
    val idLaporan: Int,
    val createdat: String,
    val deskripsi: String,
    val foto: String,
//    @SerializedName("id_kecamatan")
    val idKecamatan: String,
    val kategori: String,
    val latitude: Double,
    val longitude: Double,
//    @SerializedName("sub_kategori")
    val subKategori: String,
    val vote: Int
)
