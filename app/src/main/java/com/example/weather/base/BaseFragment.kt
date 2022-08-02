package com.example.weather.base

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment()
{
    private val networkCallback = object : ConnectivityManager.NetworkCallback()
    {
        // network is available for use
        override fun onAvailable(network: Network)
        {
            super.onAvailable(network)
            onNetworkConnected()
        }
    }

    private fun createConnectionService()
    {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        if (context == null) return
        val connectivityManager = ContextCompat.getSystemService(
            requireContext(),
            ConnectivityManager::class.java
        ) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        createConnectionService()
        init()
    }

    abstract fun onNetworkConnected()
    abstract fun init()
}