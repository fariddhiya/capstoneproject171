package com.dicoding.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.capstoneproject.databinding.ActivityMainBinding
import com.dicoding.capstoneproject.ui.aboutus.AboutUsFragment
import com.dicoding.capstoneproject.ui.home.HomeFragment
import com.dicoding.capstoneproject.ui.takephoto.TakePhotoFragment
import com.dicoding.capstoneproject.utils.Const.CUSTOM_FRAGMENT
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(onBottomNavListener)
        val fr = supportFragmentManager.beginTransaction()
        fr.add(R.id.fragment, HomeFragment())
        fr.commit()

        binding.fab.setOnClickListener{
            TakePhotoFragment().show(supportFragmentManager, CUSTOM_FRAGMENT)
        }
    }

    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        var selectedFr: Fragment = HomeFragment()
        when(i.itemId){
            R.id.navigation_home -> {
                selectedFr = HomeFragment()
            }
            R.id.navigation_abputus -> {
                selectedFr = AboutUsFragment()
            }
        }
        val fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.fragment, selectedFr)
        fr.commit()
        true
    }
}