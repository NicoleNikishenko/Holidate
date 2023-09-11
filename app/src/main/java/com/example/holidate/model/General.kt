package com.example.holidate.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Country(val countryCode: String = "", val name: String = "") : Parcelable {
    companion object {
        fun getDefaultCountry() =  Country("US", "United States")
    }
}