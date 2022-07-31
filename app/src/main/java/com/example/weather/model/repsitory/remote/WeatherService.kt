package com.example.weather.model.repsitory.remote

import com.example.weather.model.WeatherResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService
{
    private val BASE_URL = "https://api.openweathermap.org"
    private val API_KEY = "492081fb1f09b35a38643e1b3f31b571"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun fetchWeather(latitude: Double, longitude: Double): Single<WeatherResponse>
    {
        return api.getWeather(latitude = latitude, longitude = longitude, apiKey = API_KEY)
    }
}