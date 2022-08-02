package com.example.weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.ItemWeatherBinding
import com.example.weather.model.WeatherModel
import com.example.weather.model.repository.remote.WeatherResponse

class WeatherAdapter(
    private val dataSet: ArrayList<WeatherResponse>,
    private val listener: WeatherItemListener
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>()
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
        holder.view.listener = listener
    }

    fun delete(weatherResponse: WeatherResponse)
    {
        deleteAt(getPositionFor(weatherResponse))
    }

    private fun getPositionFor(weatherResponse: WeatherResponse): Int
    {
        for ((position, item) in dataSet.withIndex())
        {
            if (item.cityId == weatherResponse.cityId)
                return position
        }
        return -1
    }

    private fun deleteAt(position: Int)
    {
        if(position < 0 || position >= itemCount) return
        dataSet.removeAt(position)
        notifyItemChanged(position)
        notifyItemRangeRemoved(position, 1)
    }

    override fun getItemCount() = dataSet.size

    class WeatherViewHolder(var view: ItemWeatherBinding) : RecyclerView.ViewHolder(view.root)
}

interface WeatherItemListener
{
    fun onClick(view: View, weatherResponse: WeatherResponse)
    fun delete(weatherResponse: WeatherResponse)
}