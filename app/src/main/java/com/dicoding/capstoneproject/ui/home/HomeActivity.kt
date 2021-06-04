package com.dicoding.capstoneproject.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.dicoding.capstoneproject.databinding.ActivityHomeBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.vwList.adapter = sectionPagerAdapter
        binding.tl.setupWithViewPager(binding.vwList)

        binding.sv.apply {
            setOnCloseListener {
                this.onActionViewExpanded()
                search()
            }
        }
        supportActionBar?.elevation = 0f
    }

    private fun search() {
        binding.sv.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}