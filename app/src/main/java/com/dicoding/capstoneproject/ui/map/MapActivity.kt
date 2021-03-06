package com.dicoding.capstoneproject.ui.map

import android.Manifest
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.databinding.ActivityMapBinding
import com.dicoding.capstoneproject.model.ReportEntity
import com.dicoding.capstoneproject.ui.category.CategoryActivity
import com.dicoding.capstoneproject.utils.Const
import com.dicoding.capstoneproject.utils.Const.KEY_MAP
import com.dicoding.capstoneproject.utils.Const.KEY_PHOTO
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.skyfishjy.library.RippleBackground
import java.util.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private var reportEntity = ReportEntity()

    private var mMap: GoogleMap? = null
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private var placesClient: PlacesClient? = null
    private var predictionList: List<AutocompletePrediction>? = null

    private var mLastKnownLocation: Location? = null
    private var locationCallback: LocationCallback? = null

    private var materialSearchBar: MaterialSearchBar? = null
    private var mapView: View? = null
    private var btnFind: Button? = null
    private var rippleBg: RippleBackground? = null

    private val DEFAULT_ZOOM = 15f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null){
            reportEntity = extras.getParcelable(KEY_PHOTO)!!
            Log.d("test123", reportEntity.toString())
        }

        materialSearchBar = findViewById(R.id.searchBar)
        btnFind = findViewById(R.id.btn_find)
        rippleBg = findViewById(R.id.ripple_bg)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        mapView = mapFragment.view

        mFusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@MapActivity)
        Places.initialize(this@MapActivity, "AIzaSyBp45bjdqqWT1pvSOg4CxZc672Pv50q9ps")
        placesClient = Places.createClient(this)
        val token = AutocompleteSessionToken.newInstance()

        binding.searchBar.setOnSearchActionListener(object : OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {}
            override fun onSearchConfirmed(text: CharSequence) {
                startSearch(text.toString(), true, null, true)
            }

            override fun onButtonClicked(buttonCode: Int) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    //opening or closing a navigation drawer
                } else if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
                    binding.searchBar.disableSearch()
                }
            }
        })

        binding.searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val predictionsRequest = FindAutocompletePredictionsRequest.builder()
                    .setTypeFilter(TypeFilter.ADDRESS)
                    .setSessionToken(token)
                    .setQuery(s.toString())
                    .build()
                placesClient!!.findAutocompletePredictions(predictionsRequest)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val predictionsResponse = task.result
                            if (predictionsResponse != null) {
                                predictionList = predictionsResponse.autocompletePredictions
                                val suggestionsList: MutableList<String?> = ArrayList()
                                for (i in predictionList!!.indices) {
                                    val prediction = predictionList!!.get(i)
                                    suggestionsList.add(prediction.getFullText(null).toString())
                                }
                                binding.searchBar.updateLastSuggestions(suggestionsList)
                                if (!binding.searchBar.isSuggestionsVisible) {
                                    binding.searchBar.showSuggestionsList()
                                }
                            }
                        } else {
                            Log.i("mytag", "prediction fetching task unsuccessful")
                        }
                    }
            }
            override fun afterTextChanged(s: Editable) {}
        })

        binding.searchBar.setSuggstionsClickListener(object :
            SuggestionsAdapter.OnItemViewClickListener {
            override fun OnItemClickListener(position: Int, v: View) {
                if (position >= predictionList!!.size) {
                    return
                }
                val selectedPrediction = predictionList!![position]
                val suggestion = binding.searchBar.lastSuggestions[position].toString()
                binding.searchBar.text = suggestion
                Handler().postDelayed({ binding.searchBar.clearSuggestions() }, 1000)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    binding.searchBar.windowToken,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
                val placeId = selectedPrediction.placeId
                val placeFields = Arrays.asList(Place.Field.LAT_LNG)
                val fetchPlaceRequest = FetchPlaceRequest.builder(placeId, placeFields).build()
                placesClient!!.fetchPlace(fetchPlaceRequest)
                    .addOnSuccessListener { fetchPlaceResponse ->
                        val place = fetchPlaceResponse.place
                        Log.i("mytag", "Place found: " + place.name)
                        Log.d("salsa", placeId)
                        Log.d("salsa", placeFields.toString())
                        Log.d("salsa", place.latLng?.latitude.toString())
                        Log.d("salsa", place.latLng?.longitude.toString())
                        reportEntity.latitude = place.latLng?.latitude
                        reportEntity.longitude = place.latLng?.longitude
                        val latLngOfPlace = place.latLng
                        if (latLngOfPlace != null) {
                            mMap!!.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    latLngOfPlace,
                                    DEFAULT_ZOOM
                                )
                            )
                        }
                    }.addOnFailureListener { e ->
                        if (e is ApiException) {
                            val apiException = e as ApiException
                            apiException.printStackTrace()
                            val statusCode = apiException.statusCode
                            Log.i("mytag", "place not found: " + e.message)
                            Log.i("mytag", "status code: $statusCode")
                        }
                    }
            }
            override fun OnItemDeleteListener(position: Int, v: View) {}
        })

        binding.btnFind.setOnClickListener(View.OnClickListener {
            getDeviceLocation()
            val currentMarkerLocation = mMap!!.cameraPosition.target
            binding.rippleBg.startRippleAnimation()
            Handler().postDelayed({
                var intent = Intent(this, CategoryActivity::class.java)
                Log.d("salsa", reportEntity.toString())
                binding.rippleBg.stopRippleAnimation()
                intent.putExtra("test", reportEntity)
                startActivity(intent)
            }, 3000)
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap!!.isMyLocationEnabled = true
        mMap!!.uiSettings.isMyLocationButtonEnabled = true
        if (mapView != null && mapView!!.findViewById<View?>("1".toInt()) != null) {
            val locationButton =
                (mapView!!.findViewById<View>("1".toInt()).parent as View).findViewById<View>("2".toInt())
            val layoutParams = locationButton.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
            layoutParams.setMargins(0, 0, 40, 180)
        }

        //check if gps is enabled or not and then request user to enable it
        val locationRequest = LocationRequest.create()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient = LocationServices.getSettingsClient(this@MapActivity)
        val task = settingsClient.checkLocationSettings(builder.build())
        task.addOnSuccessListener(
            this@MapActivity
        ) { getDeviceLocation() }
        task.addOnFailureListener(
            this@MapActivity
        ) { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(this@MapActivity, 51)
                } catch (e1: SendIntentException) {
                    e1.printStackTrace()
                }
            }
        }
        mMap!!.setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener {
            if (materialSearchBar!!.isSuggestionsVisible) materialSearchBar!!.clearSuggestions()
            if (materialSearchBar!!.isSearchEnabled) materialSearchBar!!.disableSearch()
            false
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 51) {
            if (resultCode == RESULT_OK) {
                getDeviceLocation()
            }
        }
    }

    private fun getDeviceLocation() {
        mFusedLocationProviderClient!!.lastLocation
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mLastKnownLocation = task.result
                    if (mLastKnownLocation != null) {
                        mMap!!.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    mLastKnownLocation!!.latitude, mLastKnownLocation!!.longitude

                                ), DEFAULT_ZOOM
                            )
                        )
                        reportEntity.longitude = mLastKnownLocation!!.longitude
                        reportEntity.latitude = mLastKnownLocation!!.latitude
                    } else {
                        val locationRequest = LocationRequest.create()
                        locationRequest.interval = 10000
                        locationRequest.fastestInterval = 5000
                        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        locationCallback = object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                super.onLocationResult(locationResult)
                                if (locationResult == null) {
                                    return
                                }
                                mLastKnownLocation = locationResult.lastLocation
                                mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        locationResult.lastLocation.latitude,
                                        locationResult.lastLocation.longitude,
                                    ), DEFAULT_ZOOM
                                ))
                                reportEntity.longitude = locationResult.lastLocation.longitude
                                reportEntity.latitude = locationResult.lastLocation.latitude
                                mFusedLocationProviderClient!!.removeLocationUpdates(
                                    locationCallback
                                )
                            }
                        }
                        if (ActivityCompat.checkSelfPermission(
                                this,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                this,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return@addOnCompleteListener
                        }
                        mFusedLocationProviderClient!!.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            null
                        )
                    }
                } else {
                    Toast.makeText(
                        this@MapActivity,
                        "unable to get last location",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
    }
}