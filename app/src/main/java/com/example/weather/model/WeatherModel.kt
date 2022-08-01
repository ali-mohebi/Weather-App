package com.example.weather.model

import com.example.weather.model.repository.remote.WeatherResponse

class WeatherModel(val weatherResponse: WeatherResponse)
{
    override fun equals(other: Any?): Boolean
    {
        if (other !is WeatherResponse)
            return false
        return weatherResponse.cityId == other.cityId
    }

    fun weatherIconUrl(): String?
    {
        return weatherResponse.weatherList?.get(0)?.iconId
    }

    fun getTemperatureWithDegree(): String
    {
        return String.format("%.0f", weatherResponse.temperatureDetails?.temperature) + "°"
    }

    fun getCityCountry(): String
    {
        return "${weatherResponse.cityName}, ${weatherResponse.systemDetails?.countryCode}"
    }

    fun getWindSpeed(): String
    {
        return String.format("%.0f", weatherResponse.wind?.speed) + " m/s"
    }

    fun getHumidity(): String
    {
        return weatherResponse.temperatureDetails?.humidity.toString() + "%"
    }
}