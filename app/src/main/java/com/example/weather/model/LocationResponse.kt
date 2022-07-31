package com.example.weather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class LocationResponse(
    @SerializedName("name")
    val city: String?,

    @SerializedName("lat")
    val latitude: Double?,

    @SerializedName("lon")
    val longitude: Double?,

    val country: String?,

    val state: String?,
) : Parcelable
{
    fun getGeoLocation(): String
    {
        return "geo location: " +
                String.format("%.2f", latitude) + ", " +
                String.format("%.2f", longitude)
    }

    fun getFullCountry(): String?
    {
        return if (state != null)
            "$country, $state"
        else
            country
    }
}