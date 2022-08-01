package com.example.weather.model.repository.remote

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WeatherConverter
{
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherList(value: List<Weather>?): String
    {
        if (value == null) return ""

        val type: Type = object : TypeToken<List<Weather?>?>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherList(value: String?): List<Weather>
    {
        if (value == null) return listOf()

        val type = object : TypeToken<List<Weather?>?>() {}.type
        return gson.fromJson(value, type)
    }
}
