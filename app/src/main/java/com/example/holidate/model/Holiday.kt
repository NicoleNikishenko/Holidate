package com.example.holidate.model


import com.squareup.moshi.JsonClass
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


@JsonClass(generateAdapter = true)
@Parcelize
data class Holiday(
    val date: Date? = null,
    val localName: String = "",
    val name: String = "",
    val countryCode: String = "",
    val fixed: Boolean = false,
    val global: Boolean = false,
    val counties: List<String>? = listOf(),
    val launchYear: Int? = null,
    val types: List<HolidayTypes> = listOf(),

    //creating a unique holiday ID
    val id: String = "${
        name.replace(" ", "_").lowercase()
    }_${countryCode}_${date}_${counties?.joinToString("-") }",
    var isFavorite: Boolean = false,
    ) : Parcelable


enum class HolidayTypes(val jsonValue: String) {
    Public(jsonValue = "Public"),
    Bank(jsonValue = "Bank"),  // Bank holiday, banks and offices are closed
    School(jsonValue = "School"),  // School holiday, schools are closed
    Authorities(jsonValue = "Authorities"),  // Authorities are closed
    Optional(jsonValue = "Optional"),  // Majority of people take a day off
    Observance(jsonValue = "Observance")  // Optional festivity, no paid day off
}