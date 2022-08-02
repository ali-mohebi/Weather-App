package com.example.weather.interactor

import android.content.Context
import com.example.weather.model.repository.WeatherRepository
import com.example.weather.model.repository.remote.WeatherResponse
import io.reactivex.Single

class WeatherListInteractor
{
    private val repository: WeatherRepository by lazy { WeatherRepository() }

    suspend fun fetchWeatherList(context: Context): List<WeatherResponse>
    {
        return repository.fetchWeatherListLocally(context)
    }

    fun fetchRemotely(weatherResponse: WeatherResponse): Single<WeatherResponse>
    {
        return repository.fetchWeatherFromRemote(
            weatherResponse.coordination.latitude,
            weatherResponse.coordination.longitude
        )
    }

    suspend fun save(weatherResponse: WeatherResponse, context: Context)
    {
        repository.save(weatherResponse, context)
    }

    suspend fun delete(weatherResponse: WeatherResponse, context: Context)
    {
        repository.delete(weatherResponse, context)
    }
}