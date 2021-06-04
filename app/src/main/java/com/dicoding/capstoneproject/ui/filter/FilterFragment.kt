package com.dicoding.capstoneproject.ui.filter


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dicoding.capstoneproject.databinding.FragmentFilterBinding
import com.dicoding.capstoneproject.ui.filter.FilterActivity.Companion.CATEGORY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentFilterBinding: FragmentFilterBinding
    private lateinit var adapter: FilterAdapter
    private val filterViewModel : FilterViewModel by viewModels()

    companion object {
        var STATUS = "status"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFilterBinding = FragmentFilterBinding.inflate(layoutInflater, container, false)
        return fragmentFilterBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            adapter = FilterAdapter()

            loadFilterRoad()

            getStatus()
            getCategory()
        }
    }

    private fun getCategory() {
        fragmentFilterBinding.pohon.setOnClickListener(this)
        fragmentFilterBinding.jalan.setOnClickListener(this)
        fragmentFilterBinding.sampah.setOnClickListener(this)
    }

    private fun getStatus(){
        fragmentFilterBinding.waiting.setOnClickListener(this)
        fragmentFilterBinding.koordinasi.setOnClickListener(this)
        fragmentFilterBinding.diposisi.setOnClickListener(this)
        fragmentFilterBinding.proses.setOnClickListener(this)
        fragmentFilterBinding.finish.setOnClickListener(this)
        fragmentFilterBinding.denied    .setOnClickListener(this)
    }

    private fun loadFilterRoad(){
        with(fragmentFilterBinding.jalan){
            adapter = adapter
            filterViewModel.getRoad().observe(viewLifecycleOwner){
                if (it.isNotEmpty()){
                    adapter.setData(it)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v) {
            fragmentFilterBinding.waiting -> {
                STATUS = "waiting"
            }
            fragmentFilterBinding.koordinasi -> {
                STATUS = "koordinasi"
            }
            fragmentFilterBinding.diposisi -> {
                STATUS = "diposisi"
            }
            fragmentFilterBinding.proses -> {
                STATUS = "proses"
            }
            fragmentFilterBinding.finish -> {
                STATUS = "finish"
            }
            fragmentFilterBinding.denied -> {
                STATUS = "denied"
            }
            fragmentFilterBinding.pohon -> {
                CATEGORY = "pohon"
            }
            fragmentFilterBinding.jalan -> {
                CATEGORY = "jalan"
            }
            fragmentFilterBinding.sampah -> {
                CATEGORY = "sampah"
            }
        }

        val intent = Intent(requireContext(), FilterActivity::class.java)
        startActivity(intent)
    }
}
