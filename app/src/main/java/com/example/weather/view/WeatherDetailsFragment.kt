package com.example.weather.view

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
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.model.WeatherModel
import com.example.weather.utils.TAG
import com.example.weather.viewmodel.WeatherDetailsViewModel

class WeatherDetailsFragment : Fragment(), WeatherDetailsFragmentListener
{

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherDetailsViewModel by viewModels()

    private val args: WeatherDetailsFragmentArgs by navArgs()

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
        setActionUp()
        setDataBindingModels()
        observeViewModels()
        maybeFetchWeather(args.locationResponse)
    }

    private fun maybeFetchWeather(locationResponse: LocationResponse)
    {
        if (isValid(locationResponse))
            viewModel.fetchWeather(locationResponse)
        else
            updateLoadingError(true)
    }

    private fun isValid(locationResponse: LocationResponse): Boolean
    {
        if (locationResponse.latitude == null || locationResponse.longitude == null) return false
        return true
    }

    private fun updateLoadingError(errorOccurred: Boolean)
    {
        binding.textViewWeatherDetailsError.visibility = if (errorOccurred) View.VISIBLE else View.GONE
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