package com.dicoding.capstoneproject.ui.confirmationreport

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.databinding.ActivityConfirmationReportBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.model.ReportResp
import com.dicoding.capstoneproject.retrofit.API
import com.dicoding.capstoneproject.retrofit.RetroInstance
import com.dicoding.capstoneproject.ui.donecheck.DoneActivity
import com.dicoding.capstoneproject.ui.similiarreport.SimiliarReportActivity
import com.dicoding.capstoneproject.utils.Const.ADD_REPORT
import com.dicoding.capstoneproject.utils.Const.KEY_DESCRIPTION
import com.dicoding.capstoneproject.utils.Const.KEY_SIMILIAR_ENTITY
import com.dicoding.capstoneproject.utils.Const.KEY_SIMILIAR_RESPONSE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmationReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationReportBinding
    private var reportEntity = ReportEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(KEY_DESCRIPTION)!!
            Log.d("test123", reportEntity.toString())
        }

        Glide.with(this)
            .load(reportEntity.img_url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_failed)
                .error(R.drawable.ic_failed).override(1000, 1000))
            .into(binding.imgReport)
        with(binding){
            category.text = reportEntity.category
            subCategory.text = reportEntity.subcategory
            description.text = reportEntity.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.send_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_send -> {
                postApi()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun postApi(){
        val retrofit =
            RetroInstance().getRetrofitClient(ADD_REPORT).create(API::class.java)
        Log.d("farid123", reportEntity.toString())
        retrofit.uploadReport(reportEntity).enqueue(object : Callback<ReportResp> {
            override fun onResponse(call: Call<ReportResp>, response: Response<ReportResp>) {
                val responseReport = response.body()?.body
                if (responseReport?.similarSentences == null){
                    Toast.makeText(this@ConfirmationReportActivity, "Laporan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ConfirmationReportActivity, DoneActivity::class.java)
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } else{

                    val resp = response.body()?.body?.similarSentences?.get(0)
                    Log.d("fariddd", resp.toString())
                    val intents= Intent(this@ConfirmationReportActivity, SimiliarReportActivity::class.java)
                    intents.putExtra(KEY_SIMILIAR_RESPONSE, resp)
                    intents.putExtra(KEY_SIMILIAR_ENTITY, reportEntity)
                    startActivity(intents)
                }
            }

            override fun onFailure(call: Call<ReportResp>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}