package com.study.myapplication.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.study.myapplication.databinding.ActivityFlowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlowActivity : AppCompatActivity() {
    private lateinit var viewModel: FlowViewModel
    private val binding by lazy { ActivityFlowBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = FlowViewModel()

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getWeather()
            .collect {
                Log.d("현재 기온",it)
            }
        }
    }
}