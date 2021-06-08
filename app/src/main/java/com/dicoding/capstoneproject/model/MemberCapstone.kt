package com.dicoding.capstoneproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemberCapstone(
    var name: String? = null,
    var idMember: String? = null,
    var div: String? = null,
    var photo: Int = 0,
): Parcelable