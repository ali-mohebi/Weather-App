package com.example.weather.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat

class ConnectionServiceHelper
{
     fun createConnectionService(context: Context?, networkCallback: ConnectivityManager.NetworkCallback)
    {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        if (context == null) return
        val connectivityManager = ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        ) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}