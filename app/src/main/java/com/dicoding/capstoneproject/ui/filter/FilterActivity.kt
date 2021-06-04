package com.dicoding.capstoneproject.ui.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.databinding.ActivityFilterBinding
import com.dicoding.capstoneproject.ui.report.ReportGridAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding
    private val filterViewModel : FilterViewModel by viewModels()
    private lateinit var reportGridAdapter: ReportGridAdapter

    companion object {
        var CATEGORY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupReyclerView()
    }

    private fun setupReyclerView() {
        reportGridAdapter = ReportGridAdapter()

        with(binding.reportRv) {
            adapter = reportGridAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)

            when(CATEGORY) {
                "jalan" -> {
                    filterViewModel.getRoad().observe(this@FilterActivity, {
                        if (it.isNotEmpty()) {
                            reportGridAdapter.setReport(it)
                        }
                    })
                }
                "pohon" -> {
                    filterViewModel.getTree().observe(this@FilterActivity, {
                        if (it.isNotEmpty()) {
                            reportGridAdapter.setReport(it)
                        }
                    })
                }
                "sampah" -> {
                    filterViewModel.getGarbage().observe(this@FilterActivity, {
                        if (it.isNotEmpty()) {
                            reportGridAdapter.setReport(it)
                        }
                    })
                }
            }
        }
    }
}