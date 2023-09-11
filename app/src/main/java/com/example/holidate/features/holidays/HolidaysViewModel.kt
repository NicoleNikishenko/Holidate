package com.example.holidate.features.holidays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidate.model.Holiday
import com.example.holidate.repositories.FavoriteHolidaysRepository
import com.example.holidate.repositories.HolidayRepository
import com.example.holidate.room.FavoriteHoliday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HolidaysViewModel(private val holidayRepository: HolidayRepository, private val favoriteHolidaysRepository: FavoriteHolidaysRepository) : ViewModel() {

    val countryData = holidayRepository.currentCountry

    private val _holidaysList = MutableLiveData<List<Holiday>?>()
    val holidaysList: LiveData<List<Holiday>?> = _holidaysList


    fun getHolidayList(countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = holidayRepository.getHolidayList(countryCode)
            response.map { it.isFavorite = favoriteHolidaysRepository.isFavorite(it.id) }
            _holidaysList.postValue(response)
        }
    }

    fun getFavoriteHolidayList(countryCode: String): LiveData<List<FavoriteHoliday>> {
       return favoriteHolidaysRepository.getFavoriteHolidaysByCountry(countryCode)
    }


    fun addHolidayToFavorite(holiday: Holiday) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteHolidaysRepository.addToFavorite(holiday)
        }
    }

    fun removeFromFavorite(holiday: Holiday){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteHolidaysRepository.removeFromFavorite(holiday)
        }
    }

    fun clearData() {
        _holidaysList.postValue(null)
    }
}