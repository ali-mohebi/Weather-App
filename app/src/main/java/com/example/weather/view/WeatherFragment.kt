package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment(), WeatherFragmentListener
{

    private var _binding: FragmentWeatherBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        init()
    }

    private fun init()
    {
        setDataBindingModels()
    }

    private fun setDataBindingModels()
    {
        binding.listener = this
    }

    override fun onSearch()
    {
        navigateToSearch()
    }

    private fun navigateToSearch()
    {
        val direction = WeatherFragmentDirections.toSearchFragment()
        findNavController().navigate(direction)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}

interface WeatherFragmentListener
{
    fun onSearch()
}