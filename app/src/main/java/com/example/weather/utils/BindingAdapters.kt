package com.example.weather.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.example.weather.R
import com.example.weather.model.BASE_URL

@BindingAdapter("android:imageUrl")
fun ImageView.loadImage(id: String?)
{
    if (id == null) return
    val url = "$BASE_URL/img/wn/$id@2x.png"
    Glide.with(context)
        .load(url)
        .apply(getRequestOptions(context))
        .into(this)
}

fun getRequestOptions(context: Context) = RequestOptions()
    .placeholder(getProgressDrawable(context))
    .error(R.drawable.ic_weather_loading_error)
    .diskCacheStrategy(DiskCacheStrategy.DATA)

fun getProgressDrawable(context: Context): CircularProgressDrawable
{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 20f
        start()
    }
}