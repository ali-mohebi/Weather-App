package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weather.databinding.FragmentWeatherListBinding
import com.example.weather.model.repository.remote.WeatherResponse
import com.example.weather.utils.WrapContentLinearLayoutManager
import com.example.weather.viewmodel.WeatherListViewModel

class WeatherListFragment : Fragment(), WeatherListFragmentListener, WeatherItemListener
{

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherListViewModel by viewModels()
    private val _adapter = WeatherAdapter(arrayListOf(), this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        init()
    }

    private fun init()
    {
        setDataBindingModels()
        setupRecyclerView()
        observeViewModels()
        fetchWeathers()
    }

    private fun setDataBindingModels()
    {
        binding.listener = this
    }

    private fun setupRecyclerView()
    {
        if (context == null) return
        binding.recyclerViewSearch.apply {
            layoutManager = WrapContentLinearLayoutManager(context)
            adapter = _adapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
        }
    }

    private fun observeViewModels()
    {
        viewModel.weatherList.observe(viewLifecycleOwner) {
            _adapter.update(it)
            updateEmptyList(it.isEmpty())
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBarWeatherList.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun updateEmptyList(isEmpty: Boolean)
    {
        binding.textViewWeatherListEmptyList.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun fetchWeathers()
    {
        viewModel.fetchWeatherList()
    }

    override fun onSearch()
    {
        navigateToSearch()
    }

    private fun navigateToSearch()
    {
        val direction = WeatherListFragmentDirections.toSearchFragment()
        findNavController().navigate(direction)
    }

    override fun onClick(view: View, weatherResponse: WeatherResponse)
    {
        val directions =
            WeatherListFragmentDirections.toWeatherDetailsFragment(weatherResponse = weatherResponse)
        view.findNavController().navigate(directions)
    }

    override fun delete(weatherResponse: WeatherResponse)
    {
        viewModel.deleteWeather(weatherResponse)
        _adapter.delete(weatherResponse)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}

interface WeatherListFragmentListener
{
    fun onSearch()
}