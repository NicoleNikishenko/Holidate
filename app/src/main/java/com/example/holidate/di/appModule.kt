package com.example.holidate.di

import com.example.holidate.features.MainViewModel
import com.example.holidate.features.dialogs.CountrySelectViewModel
import com.example.holidate.features.holidays.HolidaysViewModel
import com.example.holidate.repositories.FavoriteHolidaysRepository
import com.example.holidate.repositories.HolidayRepository
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    //Repositories
    single { HolidayRepository(get()) }
    single { FavoriteHolidaysRepository(get())}

    //ViewModels
    viewModelOf(::MainViewModel)
    viewModelOf(::HolidaysViewModel)
    viewModelOf(::CountrySelectViewModel)

}