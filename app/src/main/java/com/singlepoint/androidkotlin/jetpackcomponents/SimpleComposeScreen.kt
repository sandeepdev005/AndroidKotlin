package com.singlepoint.androidkotlin.jetpackcomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class SimpleComposeActivity: ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContent { 
           Greetings(name = "Sandeep")
       }
    }
    
    @Composable
    fun Greetings(name : String){
        Text(text = "${name}", fontSize = 24.sp)
    }


    @Composable
    fun CounterScreen(counterViewModel: CounterViewModel = viewModel()){
        val count by counterViewModel.counter.observeAsState(0)

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Count $count")
            Button(onClick = { counterViewModel.incrementCount() }) {
                Text(text = "Increment Count")
            }
        }
    }
    
    @Composable
    @Preview(showBackground = true)
    fun DefaultPreview(){
        Greetings(name = "Sandeep")
    }

    @Composable
    @Preview(showBackground = true)
    fun CounterPreview(){
        CounterScreen()
    }
}

class CounterViewModel : ViewModel(){
    private var _counter = MutableLiveData<Int>()
    val counter : LiveData<Int> = _counter

    fun incrementCount() = (_counter.value ?: 0) + 1
}

