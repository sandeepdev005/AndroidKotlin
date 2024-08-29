package com.singlepoint.androidkotlin.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.singlepoint.androidkotlin.R
import org.w3c.dom.Text

/*Shared ViewModel created in activity and shared among all the fragments*/

class SharedViewModel : ViewModel(){
    val mySharedData : MutableLiveData<String> by lazy { MutableLiveData() }
}

class SharedActivity : AppCompatActivity(){

    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
    }
}

class FragmentA : Fragment(){

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val view =  inflater.inflate(R.layout.activity_mvvmactivity, container, false)
        view.findViewById<TextView>(R.id.tv_name).setOnClickListener {
            sharedViewModel.mySharedData.value = "Data from Fragment A"
        }
        return view
    }
}


class FragmentB : Fragment(){

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val view =  inflater.inflate(R.layout.activity_mvvmactivity, container, false)
        view.findViewById<TextView>(R.id.tv_name).setOnClickListener {
            sharedViewModel.mySharedData.value = "Data from Fragment A"
        }
        return view
    }
}

