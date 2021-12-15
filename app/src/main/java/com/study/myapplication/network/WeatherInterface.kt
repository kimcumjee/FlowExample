package com.study.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
    @GET("/data/2.5/weather?")
    fun getWeather(
        @Query("q") location: String,
        @Query("appid") apikey: String,
        @Query("units") units: String
    ): Call<WeatherDataClass>
}
