package com.dicoding.capstoneproject.ui.report

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.dicoding.capstoneproject.ui.bookmark.BookmarkViewModel
import com.dicoding.capstoneproject.utils.Helper.formatTo
import com.dicoding.capstoneproject.utils.Helper.loadImage
import com.dicoding.capstoneproject.utils.Helper.toDate
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ReportGridAdapter() : RecyclerView.Adapter<ReportGridAdapter.GridViewHolder>(){

    private var listReport = ArrayList<Value>()
    private lateinit var binding: ActivityReportBinding

    fun setReport(report: List<Value>?){
        if (report == null) return
        this.listReport.clear()
        this.listReport.addAll(report)
    }

    inner class GridViewHolder(private val binding: ActivityReportBinding):RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(reportEntity: Value) {
            with(binding) {
                descReport.text = reportEntity.deskripsi
                voteUp.text = reportEntity.vote.toString()
                reportHour.text = date(reportEntity.createdat)
                reportPhoto.loadImage(reportEntity.foto)
                reportStatus.text = reportEntity.kategori

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun date(s: String): String? {
        val parsedDate = LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        return formattedDate
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ActivityReportBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val report = listReport[position]
        holder.bind(report)
    }

    override fun getItemCount(): Int = listReport.size


    }
