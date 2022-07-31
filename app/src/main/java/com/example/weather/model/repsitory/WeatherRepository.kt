package com.example.weather.model.repsitory

import com.example.weather.model.WeatherResponse
import com.example.weather.model.repsitory.remote.WeatherService
import io.reactivex.Single

class WeatherRepository
{
    fun fetchWeatherFromDatabase(latitude: Double, longitude: Double) : Single<WeatherResponse>{
        TODO()
    }

    fun fetchWeatherFromRemote(latitude: Double, longitude: Double) : Single<WeatherResponse>{
        return WeatherService().fetchWeather(latitude, longitude)
    }
}