package com.example.weather.view

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.weather.databinding.FragmentWeatherDetailsBinding
import com.example.weather.model.WeatherModel
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.utils.ConnectionServiceHelper
import com.example.weather.utils.TAG
import com.example.weather.viewmodel.WeatherDetailsViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherDetailsFragment : Fragment(), WeatherDetailsFragmentListener
{

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherDetailsViewModel by viewModels()

    private val args: WeatherDetailsFragmentArgs by navArgs()

    private val networkCallback = object : ConnectivityManager.NetworkCallback()
    {
        // network is available for use
        override fun onAvailable(network: Network)
        {
            super.onAvailable(network)
            val locationResponse = args.locationResponse
            viewModel.onNetworkConnected(locationResponse)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        createConnectionService()
        setActionUp()
        setDataBindingModels()
        observeViewModels()
        showWeather()
    }

    private fun createConnectionService()
    {
        ConnectionServiceHelper().createConnectionService(context, networkCallback)
    }

    private fun setActionUp()
    {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarWeatherDetails.toolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )
    }
    private fun setDataBindingModels()
    {
        binding.listener = this
    }

    private fun observeViewModels()
    {
        viewModel.weatherResponse.observe(viewLifecycleOwner) {
            Log.d(TAG, "observeViewModels: weatherResponse: $it")
            binding.model = WeatherModel(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBarWeatherDetails.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingError.observe(viewLifecycleOwner) {
            updateLoadingError(it)
        }
        viewModel.savedToDatabase.observe(viewLifecycleOwner) {
            showSaveResult(it)
        }
    }

    private fun updateLoadingError(isError: Boolean)
    {
        binding.textViewWeatherDetailsError.visibility = if (isError) View.VISIBLE else View.GONE
        binding.imageViewWeatherDetailsLocationIcon.visibility =
            if (isError) View.GONE else View.VISIBLE
        binding.linearLayoutWeatherDetailsWeatherDetailsRoot.visibility =
            if (isError) View.GONE else View.VISIBLE
    }

    private fun showSaveResult(isSuccessful: Boolean)
    {
        val message = if (isSuccessful)
            "Saved successfully"
        else
            "Error saving"

        Snackbar.make(binding.buttonWeatherDetailsSave, message, Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.buttonWeatherDetailsSave)
            .show()
    }

    private fun showWeather()
    {
        if (isLocationValid(args.locationResponse))
            args.locationResponse?.let { viewModel.fetchWeather(it) }
        else
            binding.model = WeatherModel(args.weatherResponse!!)
    }

    private fun isLocationValid(locationResponse: LocationResponse?): Boolean
    {
        if (locationResponse == null) return false
        if (locationResponse.latitude == null || locationResponse.longitude == null) return false
        return true
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    override fun save()
    {
        viewModel.save()
    }
}

interface WeatherDetailsFragmentListener
{
    fun save()
}