package com.singlepoint.androidkotlin.jetpackcomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateFlowViewModel : ViewModel() {
    private val _data = MutableStateFlow("Inital Data")
    val data: StateFlow<String> = _data

    fun updateData(newData: String) {
        _data.value = newData
    }
}

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setFlowScreen()
        }
    }

    @Composable
    fun setFlowScreen(stateFlowViewModel : StateFlowViewModel = viewModel()) {
        val data by stateFlowViewModel.data.collectAsState()

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Latest data $data")
            Button(onClick = { stateFlowViewModel.updateData("new data") }) {
                Text(text = "Increment Count")
            }
        }
    }



}