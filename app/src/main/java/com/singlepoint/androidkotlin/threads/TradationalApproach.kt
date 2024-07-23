package com.singlepoint.androidkotlin.threads

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.singlepoint.androidkotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/***
 *  Threads are used to performing background task
 *  Allowing UI thread to remain responsive and handle user interaction
 *
 *  Used to perform following operation
 *  1. N/W Operation
 *  2. Data Base Operation
 *  3. File I/O
 *  4. Complex Computation
 *
 *  Important things to consideration
 *  1. Thread Safety : Shared Resources are accessed in thread safe manner to avoid race conditions
 *  2. LifeCycleAware : Avoid being crashed due to operation being performed after the component is destroyed
 *  3. Alternative : HandlerThread, ExecutorService, Kotlin Coroutines.
 * */


//Create a thread to perform background operation
class DownloadTask : Runnable {
    override fun run() {
        for (i in 1..100) {
            Thread.sleep(1000)
            print("Downloading ....$i")
        }
    }
}

//Create a thread to perform background operation and update ui with Handler
class DownloadTaskAndUpdateUIWithHandler(private val handler: Handler, val textView: TextView) :
    Runnable {
    override fun run() {
        for (i in 1..10) {
            Thread.sleep(1000)

            handler.post {
                textView.text = "Downloading the file with $i"
            }
        }
    }

}

//Create a thread to perform background operation and update ui with RunOnUIThread
class DownloadTaskWithUiThread(private val activity: AppCompatActivity)  : Runnable{
    override fun run() {
        for(i in 1..2){
            Thread.sleep(1000)

            activity.runOnUiThread{
                //Update the UI elements here
                //activity.statusTextView.text = "Downloading... $i"
            }
        }
    }
}




class SimpleThreadActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    val textView  : TextView ? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)


        //Simply performing the task
        val downloadTaskThread = Thread(DownloadTask())
        downloadTaskThread.start()


        //Perform background and update the ui with Handler
        val textView  : TextView =  findViewById(R.id.tv_name)
        val downloadTaskAndUpdateUiWithHandler = Thread(
            DownloadTaskAndUpdateUIWithHandler(handler, textView)
        )
        downloadTaskAndUpdateUiWithHandler.start()

        //Perform Bg task and update Ui using runOnUI Thread
        val downloadTaskAndUpdateUIWithRunOnUiThread = Thread(DownloadTaskWithUiThread(this))
        downloadTaskAndUpdateUIWithRunOnUiThread.start()


        //Using coroutine for background task
        lifecycleScope.launch {
            performDownload()
        }
    }

    private suspend fun performDownload(){
        withContext(Dispatchers.IO){
            for(i in 1..8){
                delay(1000)
                withContext(Dispatchers.Main){
                    textView?.text = "Downloading...... $i"
                }
            }
        }
    }
}