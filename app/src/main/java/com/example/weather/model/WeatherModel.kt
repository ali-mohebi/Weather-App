package com.example.weather.model

import com.example.weather.model.repository.remote.WeatherResponse

class WeatherModel(val weatherResponse: WeatherResponse)
{
    fun weatherIconUrl(): String?
    {
        return weatherResponse.weatherList?.get(0)?.iconId
    }

    fun getTemperatureWithDegree(): String
    {
        return String.format("%.0f", weatherResponse.temperatureDetails?.temperature) + "°"
    }

    fun getLocation(): String
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

    fun getWeatherTitle(): String
    {
        return weatherResponse.weatherList?.get(0)?.name + ", " +
                weatherResponse.weatherList?.get(0)?.description
    }
}