package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.interactor.WeatherInteractor
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.model.repository.remote.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(application: Application) : AndroidViewModel(application)
{
    private val weatherInteractor = WeatherInteractor()
    private val disposables: CompositeDisposable = CompositeDisposable()

    val loading = MutableLiveData(false)
    val loadingError = MutableLiveData(false)
    val weatherResponse = MutableLiveData<WeatherResponse>()

    fun fetchWeather(locationResponse: LocationResponse)
    {
        loading.value = true
        disposables.add(
            weatherInteractor.fetchWeather(locationResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .doAfterTerminate { loading.value = false }
                .subscribe({
                    loading.postValue(false)
                    weatherResponse.value = it
                }, {
                    loading.postValue(false)
                    loadingError.value = true
                    it.printStackTrace()
                })
        )
    }

    fun save()
    {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            weatherInteractor.save(weatherResponse.value, getApplication())
            loading.postValue(false)
        }
    }

    public override fun onCleared()
    {
        disposables.clear()
    }

}