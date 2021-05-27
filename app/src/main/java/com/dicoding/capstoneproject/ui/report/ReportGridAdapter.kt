package com.dicoding.capstoneproject.ui.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.data.ReportEntity
import com.dicoding.capstoneproject.databinding.ActivityReportBinding

class ReportGridAdapter : RecyclerView.Adapter<ReportGridAdapter.GridViewHolder>(){

    private var listReport = ArrayList<ReportEntity>()

    fun setReport(report: List<ReportEntity>?){
        if (report == null) return
        this.listReport.clear()
        this.listReport.addAll(report)
    }

    inner class GridViewHolder(private val binding: ActivityReportBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(reportEntity: ReportEntity){
            with(binding){
                descReport.text = reportEntity.descReport
                voteUp.text = reportEntity.voteUp
                voteDown.text = reportEntity.voteDown
                reportHour.text = reportEntity.hourReport
                reportStatus.text = reportEntity.statusReport
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