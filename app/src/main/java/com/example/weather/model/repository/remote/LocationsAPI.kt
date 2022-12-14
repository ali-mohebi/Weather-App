package com.example.weather.model.repository.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsAPI
{
    @GET("/geo/1.0/direct?units=metric")
    fun getLocations(
        @Query("q") city: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): Single<List<LocationResponse>>
}