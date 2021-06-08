package com.dicoding.capstoneproject.ui.similiarreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.databinding.ActivitySimiliarReportBinding
import com.dicoding.capstoneproject.model.AddReportResponse
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.model.ReportResp
import com.dicoding.capstoneproject.retrofit.API
import com.dicoding.capstoneproject.retrofit.RetroInstance
import com.dicoding.capstoneproject.ui.donecheck.DoneActivity
import com.dicoding.capstoneproject.utils.Const.ADD_REPORT
import com.dicoding.capstoneproject.utils.Const.KEY_SIMILIAR_ENTITY
import com.dicoding.capstoneproject.utils.Const.KEY_SIMILIAR_RESPONSE
import com.dicoding.capstoneproject.utils.Const.UPDATE_VOTE_REPORT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimiliarReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimiliarReportBinding
    private var reportResp = ReportResp().body?.similarSentences?.get(0)
    private var reportEntity = ReportEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimiliarReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportResp = extras.getParcelable(KEY_SIMILIAR_RESPONSE)!!
            reportEntity = extras.getParcelable(KEY_SIMILIAR_ENTITY)!!
            Log.d("test123", reportResp.toString())
        }

        val id: Int? = reportResp?.idLaporan?.toInt()

        with(binding){
            category.text = reportResp?.kategori
            subCategory.text = reportResp?.subKategori
            description.text = reportResp?.deskripsi
        }
        Glide.with(this)
            .load(reportResp?.foto)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_failed)
                .error(R.drawable.ic_failed).override(1000, 1000))
            .into(binding.imgReport)

        binding.btnAddReport.setOnClickListener {
            val retrofit =
                RetroInstance().getRetrofitClient(ADD_REPORT).create(API::class.java)
            retrofit.addReport(reportEntity).enqueue(object : Callback<AddReportResponse> {
                override fun onResponse(
                    call: Call<AddReportResponse>,
                    response: Response<AddReportResponse>
                ) {
                    Toast.makeText(this@SimiliarReportActivity, "Laporan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SimiliarReportActivity, DoneActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<AddReportResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        binding.btnOverrideReport.setOnClickListener {
            val retrofit =
                RetroInstance().getRetrofitClient(UPDATE_VOTE_REPORT).create(API::class.java)
            if (id != null) {
                retrofit.putReport(id).enqueue(object : Callback<AddReportResponse> {
                    override fun onResponse(
                        call: Call<AddReportResponse>,
                        response: Response<AddReportResponse>
                    ) {
                        Toast.makeText(this@SimiliarReportActivity, "Laporan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SimiliarReportActivity, DoneActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<AddReportResponse>, t: Throwable) {
                        Toast.makeText(this@SimiliarReportActivity, "Laporan gagal ditambahkan", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }
}