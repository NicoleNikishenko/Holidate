package com.example.holidate.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.holidate.model.HolidayTypes
import java.util.Date
import androidx.room.TypeConverter
import com.example.holidate.model.Holiday
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverters(ListConverter::class, DateConverter::class, HolidayTypesConverter::class)
@Entity(tableName = "favorite_holidays_data_table")
data class FavoriteHoliday(
    @PrimaryKey
    @ColumnInfo(name = "holiday_id")
    val id: String,
    @ColumnInfo(name = "holiday_data")
    val date: Date?,
    @ColumnInfo(name = "holiday_local_name")
    val localName: String,
    @ColumnInfo(name = "holiday_name")
    val name: String,
    @ColumnInfo(name = "holiday_country_code")
    val countryCode: String,
    @ColumnInfo(name = "holiday_fixed")
    val fixed: Boolean,
    @ColumnInfo(name = "holiday_global")
    val global: Boolean,
    @ColumnInfo(name = "holiday_countries")
    val counties: List<String>?,
    @ColumnInfo(name = "holiday_launch_year")
    val launchYear: Int?,
    @ColumnInfo(name = "holiday_types")
    val types: List<HolidayTypes>,
)

fun Holiday.createFavoriteHolidayObject(): FavoriteHoliday {
    return FavoriteHoliday(
        this.id,
        this.date,
        this.localName,
        this.name,
        this.countryCode,
        this.fixed,
        this.global,
        this.counties,
        this.launchYear,
        this.types
    )
}

fun FavoriteHoliday.createHolidayObject(): Holiday {
    return Holiday(
        this.date,
        this.localName,
        this.name,
        this.countryCode,
        this.fixed,
        this.global,
        this.counties,
        this.launchYear,
        this.types,
        this.id,
        true,
    )
}

// Define a TypeConverter for your List
class HolidayTypesConverter {
    @TypeConverter
    fun fromString(value: String): List<HolidayTypes> {
        val listType = object : TypeToken<List<HolidayTypes>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<HolidayTypes>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}


// Define a TypeConverter for your List
class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<String>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}


class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}