package com.singlepoint.androidkotlin.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.singlepoint.androidkotlin.R

/**
 * ViewModel
 * - Android Architecture Component
 * - Designed to store and manage UI data in a lifecycle aware manner
 * - It  allow data to survive configure changes like screen rotation
 * -Use Cases
 *   1. Data Persistence Across Configuration changes
 *   2. Shared Data between Multiple Fragments
 *   3. Separation of concern - separate Ui logic and business logic
 *   4. Managing UI states - Storing and Managing UI states , such as Loading State , error states etc.
 *
 * **/


/*Data Persistence Across Configuration changes*/

class SimpleViewModel : ViewModel() {
    val myData : MutableLiveData<String> by lazy { MutableLiveData() }
}


// In your Activity or Fragment
class MyActivity : AppCompatActivity() {

    private lateinit var viewModel: SimpleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)

        viewModel = ViewModelProvider(this)[SimpleViewModel::class]
        viewModel.myData.observe(this, Observer { data ->

             // Use this data to show the result in UI

        })

        //This will trigger the observer
        viewModel.myData.value ="Hello Now trigger the event, i just updated the text of live data "
    }
}


