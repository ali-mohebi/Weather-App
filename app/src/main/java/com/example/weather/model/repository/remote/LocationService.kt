package com.example.weather.model.repository.remote

import com.example.weather.model.API_KEY
import com.example.weather.model.BASE_API_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LocationService
{
    private val MAX_RESULT_LIMIT = 5

    private val api = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LocationsAPI::class.java)

    fun fetchLocation(city: String): Single<List<LocationResponse>>
    {
        return api.getLocations(city = city, limit = MAX_RESULT_LIMIT, apiKey = API_KEY)
    }
}