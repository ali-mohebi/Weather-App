package com.example.weather.model.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.repository.remote.WEATHER_TABLE_NAME
import com.example.weather.model.repository.remote.WeatherResponse

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherResponse): Long

    @Query("SELECT * FROM $WEATHER_TABLE_NAME")
    suspend fun getAllWeathers(): List<WeatherResponse>

    @Query("SELECT * FROM $WEATHER_TABLE_NAME WHERE city_id = :cityId")
    suspend fun getWeather(cityId: Int): WeatherResponse

    @Query("DELETE FROM $WEATHER_TABLE_NAME")
    suspend fun deleteAllWeathers()
}