package com.dicoding.capstoneproject.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.capstoneproject.databinding.FragmentReportListBinding
import com.dicoding.capstoneproject.ui.bookmark.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportListFragment : Fragment() {

    private lateinit var fragmentReportListBinding: FragmentReportListBinding
    private val reportViewModel: ReportViewModel by viewModels()
    private lateinit var reportGridAdapter: ReportGridAdapter
    val bookmarkViewModel: BookmarkViewModel by viewModels()

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


        if (activity != null) {
            reportGridAdapter = ReportGridAdapter()

            with(fragmentReportListBinding.reportRv) {
                adapter = reportGridAdapter
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)

                reportViewModel.getListReport().observe(viewLifecycleOwner, {
                    if (it.isNotEmpty()) {
                        reportGridAdapter.setReport(it)
                    }
                })
            }
        }
    }
}