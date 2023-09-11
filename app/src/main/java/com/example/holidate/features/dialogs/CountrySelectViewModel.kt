package com.example.holidate.features.dialogs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidate.model.Country
import com.example.holidate.repositories.HolidayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountrySelectViewModel(val holidayRepository: HolidayRepository):ViewModel() {

    private val _availableCountries = MutableLiveData<List<Country>?>()
    val availableCountries: LiveData<List<Country>?> = _availableCountries

    init {
        getAvailableCountries()
    }

    private fun getAvailableCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = holidayRepository.getAvailableCountries()
            _availableCountries.postValue(result)
        }
    }

    fun updateCurrentCountry(country: Country){
        holidayRepository.updateCurrentCountry(country)
    }
}