package com.example.weather.model.repository.remote

import com.example.weather.model.API_KEY
import com.example.weather.model.BASE_API_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService
{
    private val api = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun fetchWeather(latitude: Double, longitude: Double): Single<WeatherResponse>
    {
        return api.getWeather(latitude = latitude, longitude = longitude, apiKey = API_KEY)
    }
}