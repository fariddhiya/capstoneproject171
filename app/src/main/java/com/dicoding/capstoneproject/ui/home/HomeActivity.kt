package com.dicoding.capstoneproject.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.capstoneproject.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.vwList.adapter = sectionPagerAdapter
        activityHomeBinding.tl.setupWithViewPager(activityHomeBinding.vwList)


        supportActionBar?.elevation =0f


    }




}