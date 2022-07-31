package com.example.weather.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.ItemSearchBinding
import com.example.weather.model.LocationResponse
import com.example.weather.utils.TAG

class SearchAdapter(private val dataSet: ArrayList<LocationResponse>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), SearchItemListener
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
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSearchBinding>(
            inflater,
            R.layout.item_search,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int)
    {
        holder.view.model = dataSet[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int
    {
        return dataSet.size
    }

    class SearchViewHolder(var view: ItemSearchBinding) : RecyclerView.ViewHolder(view.root)

    override fun onClick(view: View, locationResponse: LocationResponse)
    {
        val directions = SearchFragmentDirections.toWeatherDetailsFragment(locationResponse)
        view.findNavController().navigate(directions)
    }
}

interface SearchItemListener
{
    fun onClick(view: View, locationResponse: LocationResponse)
}