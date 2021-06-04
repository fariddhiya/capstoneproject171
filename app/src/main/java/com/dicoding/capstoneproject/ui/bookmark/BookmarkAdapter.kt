package com.dicoding.capstoneproject.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.dicoding.capstoneproject.fav.Bookmark
import com.dicoding.capstoneproject.utils.Helper.loadImage

class BookmarkAdapter : PagedListAdapter<Bookmark, BookmarkAdapter.BookmarkViewHolder>(DIFFER_CALLBACK) {

    companion object{
        private val DIFFER_CALLBACK = object  : DiffUtil.ItemCallback<Bookmark>(){
            override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
                return oldItem.idLaporan == newItem.idLaporan
            }

            override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
                return oldItem == newItem
            }
        }
    }

    val differ = AsyncListDiffer(this, DIFFER_CALLBACK)
    inner class BookmarkViewHolder(val activityReportBinding: ActivityReportBinding) :
    RecyclerView.ViewHolder(activityReportBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder =
        BookmarkViewHolder(
            ActivityReportBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.activityReportBinding.apply {
            descReport.text = currentItem.deskripsi
            voteUp.text = currentItem.vote.toString()
            reportHour.text = currentItem.createdat
            reportPhoto.loadImage(currentItem.foto)
            reportStatus.text = currentItem.kategori
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}


