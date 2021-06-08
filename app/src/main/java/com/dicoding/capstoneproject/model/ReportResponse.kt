package com.dicoding.capstoneproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportResp(

    @field:SerializedName("body")
    val body: Body? = null,

    @field:SerializedName("status")
    val status: Int? = null
) : Parcelable

@Parcelize
data class Body(

    @field:SerializedName("similar_sentences")
    val similarSentences: List<SimilarSentencesItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class SimilarSentencesItem(

    @field:SerializedName("id_kecamatan")
    val idKecamatan: String? = null,

    @field:SerializedName("createdat")
    val createdat: String? = null,

    @field:SerializedName("sub_kategori")
    val subKategori: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("id_laporan")
    val idLaporan: Int? = null,

    @field:SerializedName("kategori")
    val kategori: String? = null,

    @field:SerializedName("deskripsi")
    val deskripsi: String? = null,

    @field:SerializedName("vote")
    val vote: Int? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
) : Parcelable
