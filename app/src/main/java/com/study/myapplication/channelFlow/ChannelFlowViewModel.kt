package com.study.myapplication.channelFlow

import androidx.lifecycle.ViewModel
import com.study.myapplication.model.Key
import com.study.myapplication.network.RetrofitClient
import com.study.myapplication.network.WeatherDataClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChannelFlowViewModel : ViewModel() {
    @ExperimentalCoroutinesApi
    fun channelFlow() : Flow<String> = channelFlow{
        val weatherApi = RetrofitClient.instance.api
        val callback = object : Callback<WeatherDataClass> {
            override fun onResponse(
                call: Call<WeatherDataClass>,
                response: Response<WeatherDataClass>
            ) {
                trySend(response.message())
            }

            override fun onFailure(call: Call<WeatherDataClass>, t: Throwable) {

            }
        }
        weatherApi.getWeather("seoul", Key.apikey, Key.units).enqueue(callback)
        awaitClose {
            cancel()
        }
    }
}