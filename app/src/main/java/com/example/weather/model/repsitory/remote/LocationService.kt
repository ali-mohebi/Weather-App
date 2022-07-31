package com.example.weather.model.repsitory.remote

import com.example.weather.model.LocationResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LocationService
{
    private val BASE_URL = "https://api.openweathermap.org"
    private val API_KEY = "492081fb1f09b35a38643e1b3f31b571"
    private val MAX_RESULT_LIMIT = 5

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LocationsAPI::class.java)

    fun fetchLocation(city: String): Single<List<LocationResponse>>
    {
        return api.getLocations(city = city, limit = MAX_RESULT_LIMIT, apiKey = API_KEY)
    }
}