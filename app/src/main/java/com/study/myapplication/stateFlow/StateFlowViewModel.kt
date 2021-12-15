package com.study.myapplication.stateFlow

import androidx.lifecycle.ViewModel
import com.study.myapplication.model.Key.apikey
import com.study.myapplication.model.Key.units
import com.study.myapplication.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow


class StateFlowViewModel : ViewModel() {
    private val mutableStateFlow = MutableStateFlow("")
    val stateFlow = mutableStateFlow

    fun getWeather(text: String) {
        val weatherApi = RetrofitClient.instance.api
        weatherApi.getWeather("Seoul", apikey, units).apply {
            kotlin.runCatching {
                mutableStateFlow.value = text
            }
        }
    }
}