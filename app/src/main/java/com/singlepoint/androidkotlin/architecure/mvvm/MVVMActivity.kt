package com.singlepoint.androidkotlin.architecure.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.singlepoint.androidkotlin.R

class MVVMActivity : AppCompatActivity() {

    private val viewModel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)

        viewModel.item.observe(this, Observer { items ->
            println("item size ${items.size}")
        })
    }
}