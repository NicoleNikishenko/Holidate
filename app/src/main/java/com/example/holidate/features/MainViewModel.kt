package com.example.holidate.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidate.model.Country
import com.example.holidate.repositories.HolidayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val holidayRepository: HolidayRepository): ViewModel() {

    val userData = holidayRepository.currentCountry

    fun setCurrentCountry(country: Country) {
        viewModelScope.launch(Dispatchers.IO) {
            holidayRepository.updateCurrentCountry(country)
        }
    }
}