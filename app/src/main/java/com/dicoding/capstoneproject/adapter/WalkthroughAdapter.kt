package com.dicoding.capstoneproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstoneproject.R

class WalkthroughAdapter(val context: Context) : PagerAdapter() {
    private val imgArray: IntArray = intArrayOf(
        R.drawable.report,
        R.drawable.people,
        R.drawable.city_hall,
    )

    private val titleArray= arrayOf(
        context.resources.getString(R.string.title_splash_1),
        context.resources.getString(R.string.title_splash_2),
        context.resources.getString(R.string.title_splash_3),
    )

    override fun getCount(): Int {
        return imgArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.slide_walkthrough, container, false)

        val txtTitle: TextView = view.findViewById(R.id.tv_title_splash)
        val imgSplash: ImageView = view.findViewById(R.id.img_splash)

        txtTitle.text = titleArray[position].toString()
        Glide.with(context)
            .load(imgArray[position])
            .apply(RequestOptions.overrideOf(400, 400))
            .into(imgSplash)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view: View = `object` as View
        container.removeView(view)
    }
}