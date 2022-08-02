package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weather.interactor.SearchInteractor
import com.example.weather.model.repository.remote.LocationResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application)
{
    private val searchInteractor = SearchInteractor()
    private val disposables: CompositeDisposable = CompositeDisposable()

    val loading = MutableLiveData(false)
    val loadingError = MutableLiveData(false)
    val locationResponses = MutableLiveData<List<LocationResponse>>()

    fun fetchLocations(city: String)
    {
        loading.value = true
        disposables.add(
            searchInteractor.fetchLocations(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading.value = false
                    locationResponses.value = it
                }, {
                    loading.value = false
                    loadingError.value = true
                    it.printStackTrace()
                })
        )
    }

    public override fun onCleared()
    {
        disposables.clear()
    }

}