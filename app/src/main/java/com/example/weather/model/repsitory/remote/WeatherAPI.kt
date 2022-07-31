package com.example.weather.model.repsitory.remote

import com.example.weather.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI
{
    @GET("/data/2.5/weather?units=metric")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Single<WeatherResponse>
}