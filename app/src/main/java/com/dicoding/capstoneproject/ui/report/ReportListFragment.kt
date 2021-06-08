package com.dicoding.capstoneproject.ui.report

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.dicoding.capstoneproject.databinding.FragmentReportListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ReportListFragment : Fragment() {

    private lateinit var fragmentReportListBinding: FragmentReportListBinding
    private val reportViewModel: ReportViewModel by viewModels()
    private lateinit var reportGridAdapter: ReportGridAdapter
    private lateinit var binding: ActivityReportBinding
//    private val bookmarkViewModel: BookmarkViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentReportListBinding =
            FragmentReportListBinding.inflate(layoutInflater, container, false)
        return fragmentReportListBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)

        reportGridAdapter = ReportGridAdapter()


        reportViewModel.getListReport().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                reportGridAdapter.setReport(it)

                with(fragmentReportListBinding.reportRv) {
                    adapter = reportGridAdapter
                    layoutManager = GridLayoutManager(context, 2)
                    setHasFixedSize(true)

                    showLoading(false)
                }

        }
        })
    }


    private fun showLoading(state: Boolean) {
        val delayTime = 1000L
        if (state) {
            fragmentReportListBinding.progressBar.visibility = View.VISIBLE
        } else {
            Handler().postDelayed({
                fragmentReportListBinding.progressBar.visibility = View.GONE
            }, delayTime)
        }
    }

//    private fun setFav(bookmark: Bookmark) {
//        bookmarkViewModel.isBookmark(bookmark.idLaporan)
//        bookmarkViewModel.isFav.observe(this) { dReport ->
//            with(binding) {
//                if (dReport == null) {
//                    btnBookmark.setImageResource(R.drawable.bookmark)
//                    bookmarkViewModel.isBookmark(bookmark.idLaporan)
//                    bookmarkViewModel.isFav.observe(this@ReportListFragment) {
//                        btnBookmark.setOnClickListener {
//                            btnBookmark.setImageResource(R.drawable.bookmarked)
//                            bookmarkViewModel.insertToBookmark(bookmark)
//                            Snackbar.make(
//                                binding.root,
//                                getString(R.string.insertBookmark),
//                                Snackbar.LENGTH_LONG
//                            )
//                                .show()
//                        }
//                    }
//                } else {
//                    btnBookmark.setImageResource(R.drawable.bookmarked)
//                    bookmarkViewModel.isBookmark(bookmark.idLaporan)
//                    bookmarkViewModel.isFav.observe(this@ReportListFragment) {
//                        btnBookmark.setOnClickListener {
//                            btnBookmark.setImageResource(R.drawable.bookmark)
//                            bookmarkViewModel.deleteFromBookmark(dReport)
//                            Snackbar.make(
//                                binding.root,
//                                getString(R.string.insertBookmark),
//                                Snackbar.LENGTH_LONG
//                            )
//                                .show()
//                        }
//                    }
//                }
//            }
//        }
//    }

}