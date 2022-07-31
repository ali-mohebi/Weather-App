package com.example.weather.model.repsitory

import com.example.weather.model.LocationResponse
import com.example.weather.model.repsitory.remote.LocationService
import io.reactivex.Single

class LocationRepository
{

    fun fetchLocations(city: String): Single<List<LocationResponse>>
    {
        return LocationService().fetchLocation(city)
    }
}