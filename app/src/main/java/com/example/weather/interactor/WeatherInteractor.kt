package com.example.weather.interactor

import com.example.weather.model.WeatherResponse
import com.example.weather.model.repsitory.WeatherRepository
import io.reactivex.Single


class WeatherInteractor
{
    private val repository: WeatherRepository by lazy { WeatherRepository() }
    fun fetchWeather(latitude: Double, longitude: Double): Single<WeatherResponse>
    {
        //TODO
        return repository.fetchWeatherFromRemote(latitude, longitude)
    }
}