package com.example.holidate.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteHoliday::class], version = 1)
abstract class FavoriteHolidayDatabase : RoomDatabase() {

    abstract fun subscriberDAO(): FavoriteHolidaysDAO
}