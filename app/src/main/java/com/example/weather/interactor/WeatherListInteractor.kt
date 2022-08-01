package com.example.weather.interactor

import android.content.Context
import com.example.weather.model.repository.remote.WeatherResponse
import com.example.weather.model.repository.WeatherRepository
import com.example.weather.model.repository.local.WeatherDatabase
import com.example.weather.model.repository.remote.LocationResponse
import io.reactivex.Single


class WeatherListInteractor
{
    private val repository: WeatherRepository by lazy { WeatherRepository() }

    suspend fun fetchWeatherList(context: Context): List<WeatherResponse>
    {
        return repository.fetchWeatherListFromDatabase(context)
    }
}