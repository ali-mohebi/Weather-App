package com.example.weather.view

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemSearchBinding
import com.example.weather.model.LocationResponse
import com.example.weather.utils.TAG

class SearchAdapter(private val dataSet: ArrayList<LocationResponse>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()
{

    fun update(list: List<LocationResponse>)
    {
        Log.d(TAG, "update: started")
        dataSet.clear()
        dataSet.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder
    {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int)
    {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int
    {
        TODO("Not yet implemented")
    }

    class SearchViewHolder(var view: ItemSearchBinding) : RecyclerView.ViewHolder(view.root)
}

interface SearchItemListener
{
    fun getGeoLocation()
}