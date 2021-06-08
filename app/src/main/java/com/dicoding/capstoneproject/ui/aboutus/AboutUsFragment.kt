package com.dicoding.capstoneproject.ui.aboutus

import android.accounts.Account
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.adapter.MemberAdapter
import com.dicoding.capstoneproject.databinding.FragmentAboutUsBinding
import com.dicoding.capstoneproject.model.MemberCapstone
import com.dicoding.capstoneproject.viewmodel.MemberViewModel
import java.lang.reflect.Member

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