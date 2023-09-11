package com.example.holidate.di

import androidx.room.Room
import com.example.holidate.network.ApiService
import com.example.holidate.room.FavoriteHolidayDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date


val networkModule = module {
    //RoomDataBase
    single{
        Room.databaseBuilder(
            androidApplication(),
            FavoriteHolidayDatabase::class.java,
            "favorite_holidays_database"
        ).build()
    }
    single { get<FavoriteHolidayDatabase>().subscriberDAO() }


    //Network
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}


fun provideDefaultOkhttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor())

    return httpClient.build()
}

const val BASE_URL = "https://date.nager.at/api/v3/"

fun provideRetrofit(client: OkHttpClient): Retrofit {
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
