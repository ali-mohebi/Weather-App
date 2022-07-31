package com.example.weather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.example.weather.model.Coordination
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class GeoHelper
{
    fun getCoordination(context: Context, city: String): List<Coordination>?
    {
        val latLongs: MutableList<LatLng>
        return try
        {
            val geocoder = Geocoder(context)
            val addresses: List<Address> = geocoder.getFromLocationName(city, 5)
            latLongs = getLatLongs(addresses)
            getCoordinations(latLongs)
        } catch (e: IOException)
        {
            e.printStackTrace()
            null
        }
    }

    private fun getCoordinations(latLongs: MutableList<LatLng>): List<Coordination>?
    {
        if(latLongs.isEmpty()) return null
        val coordinations = arrayListOf<Coordination>()
        for(item in latLongs){
            coordinations.add(Coordination(item.latitude, item.longitude))
        }
        return coordinations
    }

    private fun getLatLongs(addresses: List<Address>): MutableList<LatLng>
    {
        val latLongs: MutableList<LatLng> = ArrayList(addresses.size)
        for (address in addresses)
        {
            if (address.hasLatitude() && address.hasLongitude())
            {
                latLongs.add(LatLng(address.latitude, address.longitude))
            }
        }
        return latLongs
    }
}