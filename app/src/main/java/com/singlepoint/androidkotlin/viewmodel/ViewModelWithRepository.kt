package com.singlepoint.androidkotlin.viewmodel

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.singlepoint.androidkotlin.R
import java.lang.IllegalArgumentException

//Passing Repository to the ViewModel


/**
 *
 * ViewModel is a powerful component for managing UI-related data in a lifecycle-aware manner. By separating data management from UI controllers,
 * it helps create more modular, testable, and maintainable code.
 * **/
data class User(val name : String, val age : Int)

class UserRepository {
    fun getUserData(): LiveData<User> {
        val user = MutableLiveData<User>()
        user.value = User("John", 25)
        return user
    }
}


class UserViewModel(private val userRepository: UserRepository) : ViewModel(){
    val userData : LiveData<User> = userRepository.getUserData()

}


@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(userRepository) as T
        }

       throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class UserActivity : AppCompatActivity(){
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)

        val userRepository = UserRepository()
        val viewModelFactory = UserViewModelFactory(userRepository)

        userViewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        userViewModel.userData.observe(this, Observer {
            findViewById<TextView>(R.id.tv_name).text = "ViewModel holding the repository instance"
        })
    }
}