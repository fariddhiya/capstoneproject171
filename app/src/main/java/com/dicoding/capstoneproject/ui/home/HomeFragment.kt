package com.dicoding.capstoneproject.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.capstoneproject.data.Value
import com.dicoding.capstoneproject.databinding.FragmentHomeBinding
import com.dicoding.capstoneproject.ui.report.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(requireContext(), childFragmentManager)
        binding.vwList.adapter = sectionPagerAdapter
        binding.tl.setupWithViewPager(binding.vwList)
    }
}