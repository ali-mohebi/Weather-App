package com.example.weather.model

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
    val coordination: Coordination?,

    @SerializedName("weather")
    @ColumnInfo(name = "weather_list")
    val weatherList: List<Weather>?,

    @SerializedName("main")
    @Embedded(prefix = "temperature_details_")
    val temperatureDetails: TemperatureDetails?,

    @SerializedName("visibility")
    val visibility: Long?,

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

    @ColumnInfo(name = "city_id")
    @SerializedName("id")
    val cityId: String?,

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone")
    val timezone: String?,

    val cityName: String?

) : Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

@Parcelize
data class Coordination(
    @ColumnInfo(name = "latitude")
    @SerializedName("lat")
    val latitude: Double?,

    @ColumnInfo(name = "longitude")
    @SerializedName("lon")
    val longitude: Double?
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