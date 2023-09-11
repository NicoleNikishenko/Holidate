package com.example.holidate.repositories

import androidx.lifecycle.LiveData
import com.example.holidate.model.Holiday
import com.example.holidate.room.FavoriteHoliday
import com.example.holidate.room.FavoriteHolidaysDAO
import com.example.holidate.room.createFavoriteHolidayObject

class FavoriteHolidaysRepository(private val dao: FavoriteHolidaysDAO) {

    fun getFavoriteHolidaysByCountry(countryCode: String): LiveData<List<FavoriteHoliday>> {
        return dao.getAllFavoriteHolidaysByCountry(countryCode)
    }

    suspend fun isFavorite (id:String): Boolean{
        val holiday = dao.findEntityById(id)
        return holiday != null
    }

    suspend fun addToFavorite(holiday: Holiday) {
        dao.insertFavoriteHoliday(holiday.createFavoriteHolidayObject())
    }

    suspend fun removeFromFavorite(holiday: Holiday) {
        dao.deleteFavoriteHoliday(holiday.createFavoriteHolidayObject())
    }
}