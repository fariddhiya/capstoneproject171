package com.dicoding.capstoneproject.ui.takephoto

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.databinding.FragmentTakePhotoBinding
import com.dicoding.capstoneproject.model.ImageResponse
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.retrofit.API
import com.dicoding.capstoneproject.retrofit.RetroInstance
import com.dicoding.capstoneproject.ui.category.CategoryActivity
import com.dicoding.capstoneproject.ui.map.MapActivity
import com.dicoding.capstoneproject.utils.Const.KEY_PHOTO
import com.dicoding.capstoneproject.utils.Const.TAKE_PHOTO
import com.esafirm.imagepicker.features.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


class TakePhotoFragment : DialogFragment() {
    private lateinit var binding: FragmentTakePhotoBinding
    private lateinit var reportEntity: ReportEntity
    private var conditionGallery: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTakePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reportEntity = ReportEntity()
        val width = (resources.displayMetrics.widthPixels * 0.7).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.btnCamera.setOnClickListener {
            Dexter.withContext(context)
                .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener{
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        openCamera()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        if(response.isPermanentlyDenied){
                            val builderAlert = AlertDialog.Builder(context)
                            builderAlert.setTitle("Permission Denied")
                                .setMessage("Permission to access device location is permanently denied. you need to go to setting to allow the permission.")
                                .setNegativeButton("Cancel", null)
                                .setPositiveButton("OK"
                                ) { dialog, which ->
                                    val intents = Intent()
                                    intents.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                    intents.data =
                                        Uri.fromParts("package", context?.packageName, null)
                                }
                                .show()
                        } else {
                            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        pemission: PermissionRequest,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).check()
        }
        binding.btnGallery.setOnClickListener {
            openGallery()
        }

    }

    private fun openGallery(){
        conditionGallery = 1
        ImagePicker.create(this)
            .single()
            .folderMode(true)
            .limit(1)
            .start()
    }

    private fun openCamera(){
        conditionGallery = 2
        ImagePicker.cameraOnly().start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val image = ImagePicker.getFirstImageOrNull(data)
            val partImage = image.path
            val imgFile = File(partImage)

            val reqBody = imgFile.asRequestBody("multipart/form-file".toMediaTypeOrNull())
            val part = MultipartBody.Part.createFormData("filepath", imgFile.name, reqBody)

            val retrofit: Retrofit = RetroInstance().getRetrofitClient(TAKE_PHOTO)
            val uploadApi: API = retrofit.create(API::class.java)
            val call: Call<ImageResponse> = uploadApi.uploadImage(part)
            call.enqueue(object : Callback<ImageResponse>{
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    val resultImage = response.body()
                    val responseCode = response.code().toString()
                    val responseBody = response.body().toString()
                    Log.d("Farid", responseCode)
                    Log.d("Farid", responseBody)

                    reportEntity.img_url = resultImage?.bodyResponse?.filepath
                    Toast.makeText(context, resultImage?.bodyResponse?.message, Toast.LENGTH_SHORT).show()
                    activityResult()
                }
                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    Log.d("farid", t.toString())
                }
            })
        }
    }

    private var resultLauncer =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    }

    private fun activityResult(){
        if (conditionGallery == 1){
            val intent1 = Intent(context, MapActivity::class.java)
            intent1.putExtra(KEY_PHOTO, reportEntity)
            resultLauncer.launch(intent1)
        } else if(conditionGallery == 2){
            val intent2 = Intent(context, CategoryActivity::class.java)
            intent2.putExtra(KEY_PHOTO, reportEntity)
            resultLauncer.launch(intent2)
        }
    }
}
