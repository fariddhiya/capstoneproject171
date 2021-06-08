package com.dicoding.capstoneproject.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dicoding.capstoneproject.databinding.ActivityCategoryBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.utils.Const.FRAGMENT_CATEGORY
import com.dicoding.capstoneproject.utils.Const.KEY_CATEGORY
import com.dicoding.capstoneproject.utils.Const.KEY_MAP
import com.dicoding.capstoneproject.utils.Const.KEY_PHOTO
import java.io.File

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private var reportEntity = ReportEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(KEY_PHOTO)!!
            Log.d("test", reportEntity.toString())
        }

        binding.btnJalan.setOnClickListener {
            reportEntity.category = "jalan"
            val intent = Intent(this@CategoryActivity, CatJalanActivity::class.java)
            intent.putExtra(KEY_CATEGORY, reportEntity)
            startActivity(intent)
        }

        binding.btnPohon.setOnClickListener {
            reportEntity.category = "pohon"
            val intent = Intent(this@CategoryActivity, CatPohonActivity::class.java)
            intent.putExtra(KEY_CATEGORY, reportEntity)
            startActivity(intent)
        }

        binding.btnSampah.setOnClickListener {
            reportEntity.category = "sampah"
            val intent = Intent(this@CategoryActivity, CatSampahActivity::class.java)
            intent.putExtra(KEY_CATEGORY, reportEntity)
            startActivity(intent)
        }
    }
}