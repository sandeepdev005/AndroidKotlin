package com.singlepoint.androidkotlin.architecure.mvvm

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 *  MVVM (Model View ViewModel)
 *  - Architecture Design Pattern
 *  - Used to separate the User Interface logic from the business logic.
 *  - It helps to make the code more maintainable, testable, scalable
 *
 *   MODEL : Represent the data and business logic of the application
 *   VIEW: Represent UI Components such as activity , fragments ,It observes the view model
 *   VIEW MODEL : Acts as a bridge between Model and view
 *    - It holds the data and state of the UI and handles the logic to communicate with the Model
 *
 *
 *
 *     implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
 * */




//Model
data class Item(val id : Int, val name : String)


class Repository{
    fun getItems()  = listOf(Item(1,"Item 1"), Item(2, "iItem 2"), Item(3, "item 3"))
}



//ViewModel

class MyViewModel : ViewModel(){
    private val repository : Repository = Repository()
    private val _item = MutableLiveData<List<Item>>()
    val item :LiveData<List<Item>> get() = _item

    init{
        fetchItems()
    }

    private fun fetchItems(){
        _item.value = repository.getItems()
    }

}


//View
// Activity - listen to the view model observers
// UI components
//read user actions

