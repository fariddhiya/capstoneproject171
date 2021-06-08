package com.dicoding.capstoneproject.ui.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.databinding.ActivityCatPohonBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.ui.inputreport.InputDescriptionActivity
import com.dicoding.capstoneproject.utils.Const
import com.dicoding.capstoneproject.utils.Const.KEY_SUB_CATEGORY

class CatPohonActivity: AppCompatActivity(){
    private lateinit var binding: ActivityCatPohonBinding
    private var reportEntity = ReportEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatPohonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(Const.KEY_CATEGORY)!!
            Log.d("test", reportEntity.toString())
        }

        binding.btnPohonTumbang.setOnClickListener{
            sendIntent("Pohon Tumbang")
        }
        binding.btnPohonRimbun.setOnClickListener {
            sendIntent("Pohon Rimbun")
        }
    }

    private fun sendIntent(subCategory: String){
        val intent = Intent(this, InputDescriptionActivity::class.java)
        reportEntity.subcategory = subCategory
        intent.putExtra(KEY_SUB_CATEGORY, reportEntity)
        startActivity(intent)
    }
}