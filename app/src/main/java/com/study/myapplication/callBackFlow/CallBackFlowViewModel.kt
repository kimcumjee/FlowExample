package com.study.myapplication.callBackFlow

import androidx.lifecycle.ViewModel
import com.study.myapplication.model.Key.apikey
import com.study.myapplication.model.Key.units
import com.study.myapplication.network.RetrofitClient
import com.study.myapplication.network.WeatherDataClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CallBackFlowViewModel : ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun callBackFlow() = callbackFlow {
        val weatherApi = RetrofitClient.instance.api

        val callback = object : Callback<WeatherDataClass> {
            override fun onResponse(
                call: Call<WeatherDataClass>,
                response: Response<WeatherDataClass>
            ) {
                trySend(response.body()!!.main.temp)
            }

            override fun onFailure(call: Call<WeatherDataClass>, t: Throwable) {
                trySend(t.message)
            }
        }
        weatherApi.getWeather("seoul", apikey, units).enqueue(callback)
        awaitClose {
            cancel()
        }
    }
}