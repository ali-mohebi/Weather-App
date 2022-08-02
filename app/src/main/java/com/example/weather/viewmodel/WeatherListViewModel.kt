package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.interactor.WeatherListInteractor
import com.example.weather.model.repository.remote.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherListViewModel(application: Application) : AndroidViewModel(application)
{
    private val UPDATE_THRESHOLD_IN_NANOSECOND = 30 * 60 * 1000 * 1000 * 1000L

    private val weatherListInteractor = WeatherListInteractor()
    private val disposables: CompositeDisposable = CompositeDisposable()

    val loading = MutableLiveData(false)
    val weatherList = MutableLiveData<List<WeatherResponse>>()

    fun fetchWeatherList()
    {
        loading.value = true
        load()
    }

    private fun load()
    {
        viewModelScope.launch(Dispatchers.IO) {
            val list = fetchWeatherRemotely()
            validateDateAndUpdate(list)
        }
    }

    private suspend fun fetchWeatherRemotely(): List<WeatherResponse>
    {
        val list = weatherListInteractor.fetchWeatherList(getApplication())
        weatherList.postValue(list)
        loading.postValue(false)
        return list
    }

    private fun validateDateAndUpdate(list: List<WeatherResponse>)
    {
        val pendingUpdate = checkListForUpdateNeed(list)
        updateWeatherList(pendingUpdate)
    }

    private fun checkListForUpdateNeed(list: List<WeatherResponse>): List<WeatherResponse>
    {
        val currentTime = System.nanoTime()
        val pendingUpdate = arrayListOf<WeatherResponse>()
        for (item in list)
        {
            if (currentTime - item.lastUpdate > UPDATE_THRESHOLD_IN_NANOSECOND)
            {
                pendingUpdate.add(item)
            }
        }
        return pendingUpdate
    }

    private fun updateWeatherList(updateList: List<WeatherResponse>)
    {
        for (item in updateList)
            updateWeather(item)
    }

    private fun updateWeather(weatherResponse: WeatherResponse)
    {
        disposables.add(
            weatherListInteractor.fetchWeather(weatherResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    save(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun save(weatherResponse: WeatherResponse)
    {
        weatherResponse.lastUpdate = System.nanoTime()
        viewModelScope.launch(Dispatchers.IO) {
            weatherListInteractor.save(weatherResponse, getApplication())
        }
    }

    fun deleteWeather(weatherResponse: WeatherResponse)
    {
        viewModelScope.launch(Dispatchers.IO) {
            weatherListInteractor.delete(weatherResponse, getApplication())
        }
    }

    fun onNetworkConnected()
    {
        if(loading.value == false)
            load()
    }

    public override fun onCleared()
    {
        disposables.clear()
    }
}