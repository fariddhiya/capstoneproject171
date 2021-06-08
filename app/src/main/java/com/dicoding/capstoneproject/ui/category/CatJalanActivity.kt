package com.dicoding.capstoneproject.ui.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.databinding.ActivityCatJalanBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.ui.inputreport.InputDescriptionActivity
import com.dicoding.capstoneproject.utils.Const.KEY_CATEGORY
import com.dicoding.capstoneproject.utils.Const.KEY_SUB_CATEGORY

class CatJalanActivity: AppCompatActivity(){
    private lateinit var binding: ActivityCatJalanBinding
    private var reportEntity = ReportEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatJalanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(KEY_CATEGORY)!!
            Log.d("test", reportEntity.toString())
        }

        binding.btnJalanBerlubang.setOnClickListener {
            sendIntent("Jalan Berlubang")
        }
        binding.btnJalanTergenang.setOnClickListener {
            sendIntent("Jalan Tergenang")
        }
    }

    private fun sendIntent(subCategory: String){
        val intent = Intent(this, InputDescriptionActivity::class.java)
        reportEntity.subcategory = subCategory
        intent.putExtra(KEY_SUB_CATEGORY, reportEntity)
        startActivity(intent)
    }
}