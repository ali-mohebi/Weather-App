package com.example.weather.model.repository

import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.model.repository.remote.LocationService
import io.reactivex.Single

class LocationRepository
{

    fun fetchLocations(city: String): Single<List<LocationResponse>>
    {
        return LocationService().fetchLocation(city)
    }
}