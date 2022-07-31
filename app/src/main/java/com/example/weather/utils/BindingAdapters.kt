package com.example.weather.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("android:latLong")
fun TextView.latLong(latitude: String, longitude: String)
{
    val text = "geo location: " +
            String.format("%.2f", latitude) + ", " +
            String.format("%.2f", longitude)
    setText(text)
}
