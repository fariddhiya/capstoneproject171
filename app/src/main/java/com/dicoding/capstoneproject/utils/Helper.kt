package com.dicoding.capstoneproject.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dicoding.capstoneproject.R

object Helper {
    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.color.black)
            .into(this)
    }
}