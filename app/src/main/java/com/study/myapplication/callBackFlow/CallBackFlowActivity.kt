package com.study.myapplication.callBackFlow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.study.myapplication.databinding.ActivityCallBackFlowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CallBackFlowActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCallBackFlowBinding.inflate(layoutInflater) }
    private lateinit var viewModel: CallBackFlowViewModel

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = CallBackFlowViewModel()
        lifecycleScope.launch {
            callbackFlow {
                binding.btnCallBackFlow.setOnClickListener {
                    launch(Dispatchers.Main){
                        viewModel.callBackFlow().collect {
                            trySend(it)

                        }
                    }
                    close()
                }
                awaitClose()
            }.collect {
                value -> Log.d("data",value.toString())
            }
        }
//        CoroutineScope(Dispatchers.IO).launch {
//            viewModel.callBackFlow().collect {
//                Log.d("서버로 부터 받은 값", it.toString())
//            }
//        }

    }
}