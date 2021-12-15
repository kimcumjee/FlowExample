package com.study.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url = "https://api.openweathermap.org/"

class RetrofitClient {
    val retrofitBuilder: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofitBuilder.create(WeatherInterface::class.java)

    companion object {
        val instance = RetrofitClient()
    }
}