package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.interactor.WeatherInteractor
import com.example.weather.interactor.WeatherListInteractor
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.model.repository.remote.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherListViewModel(application: Application) : AndroidViewModel(application)
{
    private val weatherListInteractor = WeatherListInteractor()
    private val disposables: CompositeDisposable = CompositeDisposable()

    val loading = MutableLiveData(false)
    val loadingError = MutableLiveData(false)
    val weatherList = MutableLiveData<List<WeatherResponse>>()

    fun fetchWeatherList()
    {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val list = weatherListInteractor.fetchWeatherList(getApplication())
            weatherList.postValue(list)
            loading.postValue(false)
        }
    }

    public override fun onCleared()
    {
        disposables.clear()
    }

}