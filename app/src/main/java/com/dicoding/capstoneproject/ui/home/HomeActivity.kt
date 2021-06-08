package com.dicoding.capstoneproject.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.dicoding.capstoneproject.data.ReportResponse
import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.databinding.ActivityHomeBinding
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.dicoding.capstoneproject.ui.report.ReportViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private var reportData: ArrayList<Value> = ArrayList()
    private  lateinit var viewModel: ReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.vwList.adapter = sectionPagerAdapter
        binding.tl.setupWithViewPager(binding.vwList)

        supportActionBar?.elevation = 0f
    }


}