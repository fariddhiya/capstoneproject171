package com.dicoding.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.capstoneproject.databinding.ActivityMainBinding
import com.dicoding.capstoneproject.ui.aboutus.AboutUsFragment
import com.dicoding.capstoneproject.ui.home.HomeActivity
import com.dicoding.capstoneproject.ui.home.HomeFragment
import com.dicoding.capstoneproject.ui.home.SectionPagerAdapter
import com.dicoding.capstoneproject.ui.takephoto.TakePhotoFragment
import com.dicoding.capstoneproject.utils.Const.CUSTOM_FRAGMENT
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(HomeFragment())
        with(binding){
            bottomNavigation.show(0)
            bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.home))
            bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.add))
            bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.profile))

            bottomNavigation.setOnClickMenuListener{
                when(it.id){
                    0 -> {
                        replaceFragment(HomeFragment())
                    }
                    1 -> {
                        replaceFragment(TakePhotoFragment())
                    }
                    2 -> {
                        replaceFragment(AboutUsFragment())
                    }
                    else -> false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragment, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragment, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}