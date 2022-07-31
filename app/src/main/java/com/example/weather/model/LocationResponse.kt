package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name")
    val city: String?,

    @SerializedName("lat")
    val latitude: Double?,

    @SerializedName("lon")
    val longitude: Double?,

    val country: String?,

    val state: String?,
)