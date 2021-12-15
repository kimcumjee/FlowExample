package com.study.myapplication.channelFlow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.study.myapplication.databinding.ActivityChannelFlowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChannelFlowActivity : AppCompatActivity() {
    private val binding by lazy { ActivityChannelFlowBinding.inflate(layoutInflater) }
    private lateinit var viewModel: ChannelFlowViewModel
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ChannelFlowViewModel()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.channelFlow().collect {
                Log.d("오늘 날씨",it)
            }
        }

    }
}