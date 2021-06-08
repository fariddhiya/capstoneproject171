package com.dicoding.capstoneproject.ui.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.databinding.ActivityCatSampahBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.ui.inputreport.InputDescriptionActivity
import com.dicoding.capstoneproject.utils.Const.KEY_CATEGORY
import com.dicoding.capstoneproject.utils.Const.KEY_SUB_CATEGORY

class CatSampahActivity: AppCompatActivity(){
    private lateinit var binding: ActivityCatSampahBinding
    private var reportEntity = ReportEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatSampahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(KEY_CATEGORY)!!
            Log.d("test", reportEntity.toString())
        }

        binding.btnSampahMenumpuk.setOnClickListener{
            sendIntent("Sampah Menumpuk")
        }
        binding.btnSampahBerbau.setOnClickListener {
            sendIntent("Sampah Berbau")
        }
        binding.btnSampahBerantakan.setOnClickListener {
            sendIntent("Sampah Berantakan")
        }
    }

    private fun sendIntent(subCategory: String){
        val intent = Intent(this, InputDescriptionActivity::class.java)
        reportEntity.subcategory = subCategory
        intent.putExtra(KEY_SUB_CATEGORY, reportEntity)
        startActivity(intent)
    }
}