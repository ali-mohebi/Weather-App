package com.example.weather.model.repository.remote

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

const val WEATHER_TABLE_NAME = "weathers"

@Parcelize
@Entity(tableName = WEATHER_TABLE_NAME)
data class WeatherResponse(
    @SerializedName("coord")
    @Embedded(prefix = "coordination_")
    val coordination: Coordination,

    @SerializedName("weather")
    @ColumnInfo(name = "weather_list")
    val weatherList: List<Weather>?,

    @SerializedName("main")
    @Embedded(prefix = "temperature_details_")
    val temperatureDetails: TemperatureDetails?,

    @SerializedName("visibility")
    val visibility: Long?,

    @SerializedName("wind")
    @Embedded(prefix = "wind_")
    val wind: Wind?,

    @SerializedName("clouds")
    @Embedded(prefix = "clouds_")
    val cloudinessInPercent: Cloud?,

    @SerializedName("rain")
    @Embedded(prefix = "rain_volume_")
    val rainVolume: RainVolume?,

    @SerializedName("snow")
    @Embedded(prefix = "snow_volume_")
    val snowVolume: SnowVolume?,

    @SerializedName("sys")
    @Embedded
    val systemDetails: SystemDetails?,

    @PrimaryKey
    @ColumnInfo(name = "city_id")
    @SerializedName("id")
    val cityId: Int,

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone")
    val timezone: String?,

    @SerializedName("name")
    val cityName: String?,

    @ColumnInfo(name = "last_update")
    var lastUpdate: Long = 0
) : Parcelable

@Parcelize
data class Coordination(
    @ColumnInfo(name = "latitude")
    @SerializedName("lat")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    @SerializedName("lon")
    val longitude: Double
) : Parcelable

@Parcelize
data class Weather(
    val id: Long,

    @SerializedName("main")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("icon")
    val iconId: String?
) : Parcelable

@Parcelize
data class TemperatureDetails(
    @SerializedName("temp")
    val temperature: Double?,

    @SerializedName("humidity")
    val humidity: Int?,

    @SerializedName("pressure")
    val atmosphericPressure: Int?,

    @ColumnInfo(name = "feels_like")
    @SerializedName("feels_like")
    val feelsLike: Double?,

    @ColumnInfo(name = "temp_min")
    @SerializedName("temp_min")
    val minTemperature: Double?,

    @ColumnInfo(name = "temp_max")
    @SerializedName("temp_max")
    val maxTemperature: Double?,
) : Parcelable

@Parcelize
data class SystemDetails(
    @SerializedName("country")
    val countryCode: String?
) : Parcelable

@Parcelize
data class Wind(
    val speed: Double?,

    @SerializedName("deg")
    val degree: Int?,

    val gust: Double?,
) : Parcelable

@Parcelize
data class Cloud(
    @ColumnInfo(name = "cloudiness_in_percent")
    @SerializedName("all")
    val cloudinessInPercent: Int?
) : Parcelable

@Parcelize
data class RainVolume(
    @ColumnInfo(name = "in_last_1_hour")
    @SerializedName("1h")
    val inLast1Hour: Double?,

    @ColumnInfo(name = "in_last_3_hours")
    @SerializedName("3h")
    val inLast3Hours: Double?
) : Parcelable

@Parcelize
data class SnowVolume(
    @ColumnInfo(name = "in_last_1_hour")
    @SerializedName("1h")
    val inLast1Hour: Double?,

    @ColumnInfo(name = "in_last_3_hours")
    @SerializedName("3h")
    val inLast3Hours: Double?
) : Parcelable