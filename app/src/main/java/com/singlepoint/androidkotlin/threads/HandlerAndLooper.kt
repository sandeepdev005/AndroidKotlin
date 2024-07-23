package com.singlepoint.androidkotlin.threads

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.singlepoint.androidkotlin.R

/**
 * Handler and Looper
 *
 * Handler and Looper classes in android  works together to manage and handle asynchronous task and message Queue
 *
 *  Looper :
 *       It continuously retrieves messages from the MessageQueue and processes them.
 *       Used for both Ui task and background task
 *       Loopers are tied to a specific thread and manage message queue.
 *  Handler :
 *       Posts messages and runnables to a thread’s message queue.
 *       It processes messages and tasks on the thread associated with its Looper.
 *
 * **/

class HandlerLooperActivity : AppCompatActivity(){

    private lateinit var backgroundThread : HandlerThread
    private lateinit var backgroundHandler: Handler
    private lateinit var uiHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.)


        backgroundThread = HandlerThread("BackgroundThreadwithlooper")
        backgroundThread.start()


        backgroundHandler = Handler(backgroundThread.looper){
            val message  = it
            // Process background message
            if(message.what == 1){
                // Simulate a long-running operation
                Thread.sleep(2000)
                // Post result to UI thread
                uiHandler.sendMessage(Message.obtain().apply {
                    what =1
                    obj = "Background Task COmpleted "
                })
            }
            true
        }

        uiHandler = Handler(Looper.getMainLooper()){
            val message = it
            //Process the UI Message
            if(message.what == 1){
                val uiData  = message.obj as String
                //Update UI
                //Textview.text  = uiData
            }
            true
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundThread.quitSafely()
    }
}