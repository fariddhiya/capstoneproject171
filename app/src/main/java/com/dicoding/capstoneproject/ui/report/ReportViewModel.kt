package com.dicoding.capstoneproject.ui.report

import androidx.lifecycle.ViewModel
import com.dicoding.capstoneproject.data.ReportEntity
import com.dicoding.capstoneproject.utils.DataDummy

class ReportViewModel:ViewModel() {

    fun getReports(): List<ReportEntity> = DataDummy.generateDummyReport()
}