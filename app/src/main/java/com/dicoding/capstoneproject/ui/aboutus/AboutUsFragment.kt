package com.dicoding.capstoneproject.ui.aboutus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstoneproject.adapter.MemberAdapter
import com.dicoding.capstoneproject.databinding.FragmentAboutUsBinding
import com.dicoding.capstoneproject.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : Fragment() {
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            memberViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MemberViewModel::class.java]
            val member = memberViewModel.getMember()
            val memberAdapter = MemberAdapter(requireActivity())
            memberAdapter.setMember(member)

            with(binding.rvMember){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = memberAdapter
                Log.d("Fahmi", "Halo")
            }
        }
    }
}