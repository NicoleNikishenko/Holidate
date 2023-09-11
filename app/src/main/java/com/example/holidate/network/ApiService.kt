package com.example.holidate.network

import com.example.holidate.model.Country
import com.example.holidate.model.Holiday
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("PublicHolidays/{year}/{country_code}")
    suspend fun getHolidayList(
        @Path(value = "year") year: Int,
        @Path(value = "country_code") countryCode: String,
    ): List<Holiday>

    @GET("AvailableCountries")
    suspend fun getAvailableCountries(
    ): List<Country>

}