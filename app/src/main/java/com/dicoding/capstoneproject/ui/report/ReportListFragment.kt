package com.dicoding.capstoneproject.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.capstoneproject.databinding.FragmentReportListBinding

class ReportListFragment : Fragment() {

  private lateinit var fragmentReportListBinding: FragmentReportListBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentReportListBinding = FragmentReportListBinding.inflate(layoutInflater, container, false)
        return fragmentReportListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ReportViewModel::class.java]
            val report = viewModel.getReports()
            val reportAdapter = ReportGridAdapter()
            reportAdapter.setReport(report)

            with(fragmentReportListBinding.reportRv){
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = reportAdapter

            }
        }


    }

//    private fun showRecyclerGrid(){
//        fragmentReportListBinding.reportRv.layoutManager = GridLayoutManager(context, 2)
//        val reportGridAdapter = ReportGridAdapter(list)
//        fragmentReportListBinding.reportRv.adapter = reportGridAdapter
//    }
}