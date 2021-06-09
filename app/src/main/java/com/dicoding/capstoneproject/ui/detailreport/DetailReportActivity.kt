package com.dicoding.capstoneproject.ui.detailreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.capstoneproject.databinding.ActivityDetailReportBinding
import android.os.Build
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.fav.Bookmark
import com.dicoding.capstoneproject.ui.bookmark.BookmarkViewModel
import com.dicoding.capstoneproject.utils.Helper.loadImage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailReportActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailReportBinding
    private val detailViewModel : DetailViewModel by viewModels()
    private val bookmarkViewModel : BookmarkViewModel by viewModels()


    companion object {
        var EXTRA_MOVE = "EXTRA_ID"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Report Detail"

        val extras = intent.extras
        if (extras != null) {
            val reportId = intent.getIntExtra(EXTRA_MOVE, 0)
            detailViewModel.getDetailReport(reportId).observe(this){
                showDetail(it)

                val report = Bookmark(
                    it.idLaporan,
                    it.createdat,
                    it.deskripsi,
                    it.foto,
                    it.idKecamatan,
                    it.kategori,
                    it.latitude,
                    it.longitude,
                    it.subKategori,
                    it.vote
                )
                setFav(report)
            }

        }


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDetail(report: Value){
        var status = report
        with(binding){
            if (report != null){
                categorySub.text = report.deskripsi
                dateReport.text = date(report.createdat)
                categoryReport.text = report.kategori
                reportImage.loadImage(report.foto)


            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun date(s: String): String? {
        val parsedDate = LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        return formattedDate
    }


    private fun setFav(bookmark: Bookmark) {
        bookmarkViewModel.isBookmark(bookmark.idLaporan)
        bookmarkViewModel.isFav.observe(this) { dReport ->
            with(binding) {
                if (dReport == null) {
                    fabFavorite.setImageResource(R.drawable.bookmark)
                    bookmarkViewModel.isBookmark(bookmark.idLaporan)
                    bookmarkViewModel.isFav.observe(this@DetailReportActivity) {
                        fabFavorite.setOnClickListener {
                            fabFavorite.setImageResource(R.drawable.bookmarked)
                            bookmarkViewModel.insertToBookmark(bookmark)
                            Snackbar.make(
                                binding.root,
                                getString(R.string.insertBookmark),
                                Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                } else {
                    fabFavorite.setImageResource(R.drawable.bookmarked)
                    bookmarkViewModel.isBookmark(bookmark.idLaporan)
                    bookmarkViewModel.isFav.observe(this@DetailReportActivity) {
                        fabFavorite.setOnClickListener {
                            fabFavorite.setImageResource(R.drawable.bookmark)
                            bookmarkViewModel.deleteFromBookmark(dReport)
                            Snackbar.make(
                                binding.root,
                                getString(R.string.removeBookmark),
                                Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            }
        }
    }


}