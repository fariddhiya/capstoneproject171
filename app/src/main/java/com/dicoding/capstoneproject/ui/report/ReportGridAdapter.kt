package com.dicoding.capstoneproject.ui.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.dicoding.capstoneproject.ui.bookmark.BookmarkViewModel
import com.dicoding.capstoneproject.utils.Helper.loadImage

class ReportGridAdapter() : RecyclerView.Adapter<ReportGridAdapter.GridViewHolder>(){

    private var listReport = ArrayList<Value>()
    private lateinit var binding: ActivityReportBinding

    fun setReport(report: List<Value>?){
        if (report == null) return
        this.listReport.clear()
        this.listReport.addAll(report)
    }

    inner class GridViewHolder(private val binding: ActivityReportBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(reportEntity: Value) {
            with(binding) {
                descReport.text = reportEntity.deskripsi
                voteUp.text = reportEntity.vote.toString()
                reportHour.text = reportEntity.createdat
                reportPhoto.loadImage(reportEntity.foto)
                reportStatus.text = reportEntity.kategori

//                bookmarkViewModel.isBookmark(reportEntity.idLaporan)
//                bookmarkViewModel.isFav.observe(LifecycleOwner ) { dataReport ->
//                    with(binding) {
//                        if (dataReport == null) {
//                            btnBookmark.isChecked = false
//                            bookmarkViewModel.isFav.observe(this@ReportGridAdapter)
//
//                        }
//                    }
//
//                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ActivityReportBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val report = listReport[position]
        holder.bind(report)
    }

    override fun getItemCount(): Int = listReport.size


    }
