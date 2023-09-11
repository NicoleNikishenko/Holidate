package com.example.holidate.features

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.holidate.databinding.ActivityMainBinding
import com.example.holidate.features.dialogs.CountrySelectBottomSheet
import com.example.holidate.model.Country
import com.example.holidate.utils.getCountry
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUi()
        initObservers()
        getLocation()
    }

    private fun initUi() {
        binding.apply {
            mainActivityHeaderButton.setOnClickListener {
                openCountrySelection()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        viewModel.userData.observe(this) {
            binding.mainActivityHeaderTitle.text = "Holidays In ${it.name}"
        }
    }

    private fun openCountrySelection() {
        val fragment = CountrySelectBottomSheet.newInstance()
        fragment.show(supportFragmentManager, "my_bottom_sheet_fragment_tag")
    }

    private val locationPermissionRequestCode = 1
    private fun getLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission granted, proceed to get the location
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                // Got last known location. In some rare situations this can be null.
                val country = location.getCountry(this) ?: Country.getDefaultCountry()
                viewModel.setCurrentCountry(country)
            }
        } else {
            // Request the location permission from the user
            openLocationDialog()
        }
    }

    // Custom location dialog to explain the user why we request permission
    private fun openLocationDialog() {
        val builder: AlertDialog.Builder = this@MainActivity.let {
            AlertDialog.Builder(it)
        }
        builder.apply {
            setTitle("Mind sharing your location?")
            setMessage("Want the best holiday list for where you are? We can use your location or you can pick a country. Your choice! ")
            setNeutralButton("Choose country") { _, _ ->
               openCountrySelection()
            }
            setPositiveButton("Yes, use location") { _, _ ->
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    locationPermissionRequestCode
                )
            }
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionRequestCode) {
            if (grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED) {
                getLocation()
            } else {
                // Set default
                viewModel.setCurrentCountry(Country.getDefaultCountry())
            }
            return
        }
    }


}


