package com.example.holidate.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteHolidaysDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteHoliday(holiday: FavoriteHoliday)

    @Delete
    suspend fun deleteFavoriteHoliday(holiday: FavoriteHoliday)

    @Query("SELECT * FROM favorite_holidays_data_table")
    fun getAllFavoriteHolidays(): LiveData<List<FavoriteHoliday>>

    @Query("SELECT * FROM favorite_holidays_data_table WHERE holiday_country_code = :countryCode")
    fun getAllFavoriteHolidaysByCountry(countryCode: String): LiveData<List<FavoriteHoliday>>

    @Query("SELECT * FROM favorite_holidays_data_table WHERE holiday_id = :entityId")
    suspend fun findEntityById(entityId: String): FavoriteHoliday?
}