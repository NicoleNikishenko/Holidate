package com.example.holidate.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.holidate.model.Country
import com.example.holidate.model.Holiday
import com.example.holidate.network.ApiService
import java.util.Calendar

class HolidayRepository(private val api: ApiService) {
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    private val _currentCountry = MutableLiveData<Country>()
    val currentCountry: LiveData<Country> = _currentCountry

    fun updateCurrentCountry(country: Country) {
        _currentCountry.postValue(country)
    }

    suspend fun getHolidayList(countryCode: String): List<Holiday> {
        return try {
            api.getHolidayList(currentYear, countryCode)
        } catch (ex: Exception) {
            Log.e("HolidayRepository", ex.message.toString())
            listOf()
        }
    }

    //Since this data is small and not likely to change, keeping a reference to save on api calls
    private var availableCountries: List<Country> = listOf()
    suspend fun getAvailableCountries(): List<Country> {
        return try {
            if (availableCountries.isEmpty()) {
                availableCountries = api.getAvailableCountries()
            }
            return availableCountries
        } catch (ex: Exception) {
            Log.e("HolidayRepository", ex.message.toString())
            listOf()
        }
    }

}
