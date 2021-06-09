package com.dicoding.capstoneproject.ui.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.dicoding.capstoneproject.utils.Helper.loadImage

class FilterAdapter: RecyclerView.Adapter<FilterAdapter.GridViewHolder>() {

    private var listFilter = ArrayList<Value>()

    fun setData(filter:List<Value>?){
        if (filter == null) return
        this.listFilter.clear()
        this.listFilter.addAll(filter)
    }

    inner class GridViewHolder(private val binding: ActivityReportBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(reportEntity: Value) {
            with(binding) {
                descReport.text = reportEntity.deskripsi
                voteUp.text = reportEntity.vote.toString()
                reportHour.text = reportEntity.createdat
                reportPhoto.loadImage(reportEntity.foto)
                reportStatus.text = reportEntity.kategori
            }
        }
    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): GridViewHolder {
        val binding = ActivityReportBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val filter = listFilter[position]
        holder.bind(filter)
    }

    override fun getItemCount(): Int= listFilter.size
}