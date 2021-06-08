package com.dicoding.capstoneproject.ui.walkthrough

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.viewpager.widget.ViewPager
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.adapter.WalkthroughAdapter
import com.dicoding.capstoneproject.databinding.ActivityWalkthroughBinding

class WalkthroughActivity : AppCompatActivity() {
    lateinit var binding: ActivityWalkthroughBinding
    private lateinit var wkAdapter: WalkthroughAdapter
    private val point = arrayOfNulls<TextView>(3)
    private var currentPage: Int = 0
    private val source = "&#8226;"
    private lateinit var pre: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkthroughBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pre = SharedPreferences(this)
        wkAdapter = WalkthroughAdapter(this)
        binding.vpWalkthrough.adapter = wkAdapter
        pointIndicator(currentPage)

        init()
    }

    private fun pointIndicator(p: Int){
        binding.pointIndicator.removeAllViews()
        for (i in 0..point.size - 1){
            point[i] = TextView(this)
            point[i]?.textSize = 35f
            point[i]?.setTextColor(ContextCompat.getColor(this, R.color.oslo_grey))
            point[i]?.text = HtmlCompat.fromHtml(source, HtmlCompat.FROM_HTML_MODE_LEGACY )
            binding.pointIndicator.addView(point[i])
        }

        if(point.size > 0){
            point[p]?.setTextColor(ContextCompat.getColor(this, R.color.design_default_color_primary))
        }
    }

    private fun init(){
        binding.vpWalkthrough.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                pointIndicator(position)
                currentPage = position

                if (position == point.size - 1){
                    binding.tvLanjutkan.setOnClickListener{
                        val intent = Intent(this@WalkthroughActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        binding.tvLanjutkan.setOnClickListener{
            if (binding.vpWalkthrough.currentItem + 1 < point.size){
                binding.vpWalkthrough.currentItem += 1
            } else {
                pre.firstInstall = true
                val intent = Intent(this@WalkthroughActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.tvLewati.setOnClickListener{
            pre.firstInstall = true
            val intent = Intent(this@WalkthroughActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}