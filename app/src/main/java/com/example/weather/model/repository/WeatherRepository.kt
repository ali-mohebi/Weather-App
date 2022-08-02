package com.example.weather.model.repository

import android.content.Context
import com.example.weather.model.repository.local.WeatherDatabase
import com.example.weather.model.repository.remote.WeatherResponse
import com.example.weather.model.repository.remote.WeatherService
import dagger.hilt.android.internal.Contexts.getApplication
import io.reactivex.Single

class WeatherRepository
{
    suspend fun fetchWeatherListFromDatabase(context: Context): List<WeatherResponse>
    {
        return WeatherDatabase(getApplication(context)).weatherDao().getAllWeathers()
    }

    suspend fun fetchWeatherFromDatabase(cityId: Int, context: Context): WeatherResponse
    {
        return WeatherDatabase(getApplication(context)).weatherDao().getWeather(cityId)
    }

    fun fetchWeatherFromRemote(latitude: Double, longitude: Double): Single<WeatherResponse>
    {
        return WeatherService().fetchWeather(latitude, longitude)
    }

    suspend fun save(weatherResponse: WeatherResponse, context: Context)
    {
        val dao = WeatherDatabase(context).weatherDao()
        dao.insert(weatherResponse)
    }
}