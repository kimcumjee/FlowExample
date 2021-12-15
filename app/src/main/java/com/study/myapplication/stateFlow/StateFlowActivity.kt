package com.study.myapplication.stateFlow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.study.myapplication.callBackFlow.CallBackFlowActivity
import com.study.myapplication.channelFlow.ChannelFlowActivity
import com.study.myapplication.databinding.ActivityStateFlowBinding
import com.study.myapplication.flow.FlowActivity
import kotlinx.coroutines.flow.collect

class StateFlowActivity : AppCompatActivity() {
    private lateinit var viewModel: StateFlowViewModel
    private val binding by lazy { ActivityStateFlowBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = StateFlowViewModel()
        binding.btnIntentFlow.setOnClickListener {
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
        }
        binding.btnIntentCallBackFlow.setOnClickListener {
            val intent = Intent(this, CallBackFlowActivity::class.java)
            startActivity(intent)
        }
        binding.btnIntentChannelFlow.setOnClickListener {
            val intent = Intent(this, ChannelFlowActivity::class.java)
            startActivity(intent)
        }
        binding.btnGetWeather.setOnClickListener {
            viewModel.getWeather(binding.et.text.toString())
        }

        //stateflow 예제
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect {
                Log.d("stateflow 날씨", it)

            }
        }
        viewModel.stateFlow.asLiveData().observe(this,{
            Log.d("stateflow + livedata 날씨",it)
        })
    }
}
