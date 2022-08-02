package com.example.weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.ItemWeatherBinding
import com.example.weather.model.WeatherModel
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.model.repository.remote.WeatherResponse

class WeatherAdapter(private val dataSet: ArrayList<WeatherResponse>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(), WeatherItemListener
{

    fun update(list: List<WeatherResponse>)
    {
        dataSet.clear()
        dataSet.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemWeatherBinding>(
            inflater,
            R.layout.item_weather,
            parent,
            false
        )
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int)
    {
        holder.view.model = WeatherModel(dataSet[position])
        holder.view.listener = this
    }

    override fun getItemCount(): Int
    {
        return dataSet.size
    }

    class WeatherViewHolder(var view: ItemWeatherBinding) : RecyclerView.ViewHolder(view.root)

    override fun onClick(view: View, weatherResponse: WeatherResponse)
    {
        val directions = WeatherListFragmentDirections.toWeatherDetailsFragment(weatherResponse = weatherResponse)
        view.findNavController().navigate(directions)
    }

    override fun delete(weatherResponse: WeatherResponse)
    {

    }
}

interface WeatherItemListener
{
    fun onClick(view: View, weatherResponse: WeatherResponse)
    fun delete(weatherResponse: WeatherResponse)
}