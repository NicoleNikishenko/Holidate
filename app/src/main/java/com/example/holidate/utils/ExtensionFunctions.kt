package com.example.holidate.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.location.Location
import com.example.holidate.model.Country
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Location.getCountry(context: Context): Country? {
    val geocoder = Geocoder(context, Locale.getDefault())

    try {
        // Get the user's current location based on latitude and longitude
        val addresses: List<Address>? = geocoder.getFromLocation(this.latitude, this.longitude, 1,)
        addresses?.let {
            val address: Address? = addresses.getOrNull(0)
            address?.let {
                return Country(address.countryCode, address.countryName)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    // Handle cases where the country cannot be determined
    return null
}


fun Date?.parseDate(): String {
    //Fri, Feb 12
    if (this == null) return ""
    val dateFormat = SimpleDateFormat("EEEE, MMM dd", Locale.getDefault())
    return dateFormat.format(this)
}