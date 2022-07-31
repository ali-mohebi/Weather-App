package com.example.weather.interactor

import com.example.weather.model.LocationResponse
import com.example.weather.model.repsitory.LocationRepository
import io.reactivex.Single

class SearchInteractor
{
    fun fetchLocations(city: String): Single<List<LocationResponse>>
    {
        return LocationRepository().fetchLocations(city)
    }
}