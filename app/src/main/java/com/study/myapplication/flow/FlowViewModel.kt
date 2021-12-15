package com.study.myapplication.flow

import androidx.lifecycle.ViewModel
import com.study.myapplication.model.Key.apikey
import com.study.myapplication.model.Key.units
import com.study.myapplication.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {
    suspend fun getWeather(): Flow<String> = flow {
        val weatherApi = RetrofitClient.instance.api
        weatherApi.getWeather("Seoul", apikey, units).apply {
            kotlin.runCatching {
                emit(execute().body()!!.main.temp.toString())
            }
        }
    }
}