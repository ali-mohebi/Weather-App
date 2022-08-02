package com.example.weather.interactor

import android.content.Context
import com.example.weather.model.repository.WeatherRepository
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.model.repository.remote.WeatherResponse
import io.reactivex.Single

class WeatherInteractor
{
    private val repository: WeatherRepository by lazy { WeatherRepository() }

    fun fetchWeather(locationResponse: LocationResponse): Single<WeatherResponse>
    {
        return repository.fetchWeatherFromRemote(locationResponse.latitude!!, locationResponse.longitude!!)
    }

    suspend fun save(weatherResponse: WeatherResponse?, context: Context)
    {
        if (weatherResponse == null) return
        repository.save(weatherResponse, context)
    }
}