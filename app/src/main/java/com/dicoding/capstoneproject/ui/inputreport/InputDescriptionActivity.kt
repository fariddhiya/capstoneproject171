package com.dicoding.capstoneproject.ui.inputreport

import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.dicoding.capstoneproject.databinding.ActivityInputDescReportBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.ui.confirmationreport.ConfirmationReportActivity
import com.dicoding.capstoneproject.utils.Const.KEY_DESCRIPTION
import com.dicoding.capstoneproject.utils.Const.KEY_SUB_CATEGORY
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.*

class InputDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDescReportBinding
    private var reportEntity = ReportEntity()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDescReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(KEY_SUB_CATEGORY)!!
            Log.d("test", reportEntity.toString())
        }

        if (reportEntity.latitude == 0.0){
            getLocation()
        }


        binding.floatingActionButton.setOnClickListener{ view ->

            reportEntity.description = binding.textField.text.toString()
            val intent = Intent(this@InputDescriptionActivity, ConfirmationReportActivity::class.java)
            intent.putExtra(KEY_DESCRIPTION, reportEntity)
            Log.d("farid", reportEntity.toString())
            startActivity(intent)
        }
    }

    private fun getLocation(){
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            val location: Location = task.result
            try {
                val geocoder: Geocoder = Geocoder(
                    this@InputDescriptionActivity,
                    Locale.getDefault()
                )
                val address = geocoder.getFromLocation(
                    location.latitude, location.longitude, 1
                )
                reportEntity.latitude = address[0].latitude
                reportEntity.longitude = address[0].longitude
                Log.d("farid", reportEntity.toString())
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d("farid", e.printStackTrace().toString())
            }

        }
    }
}