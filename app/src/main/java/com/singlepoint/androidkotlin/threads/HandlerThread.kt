package com.singlepoint.androidkotlin.threads

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.appcompat.app.AppCompatActivity
import com.singlepoint.androidkotlin.R

/****
 *  HandlerThread
 *
 *  1.Handler Thread is a type of thread that comes with with a "LOOPER" attached , allowing you to create message and process message (Runnable on bg thread)
 *    Useful for performing background task without blocking the Main Thread
 *  2. Handle task sequentially
 *
 *  Usecase : download the file in background without blocking the Main Thread
 *
 *  Benefits:
 *  Background Processing
 *  Sequential Task Handling -  Process tasks sequentially on the background thread.
 *  LifeCycleManagement
 *
 *
 *  Consideration:
 *  Ensure that you handle thread lifecycle properly to avoid memory leaks.
 *   For more complex use cases or multiple concurrent operations,
 *   consider using other concurrency frameworks like ExecutorService or Kotlin Coroutines.
 *
 * **/



class DownloadHandlerThread(name : String) : HandlerThread(name){

    lateinit var handler : Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()

        handler = Handler(looper){message ->
            val url = message.obj as String
            downloadFile(url)
            true
        }
    }

    private fun downloadFile(url : String){
        try{
            Thread.sleep(2000)
            println("File is downloading from $url")
        }catch (e : InterruptedException){
            e.printStackTrace()
        }

    }
}


class HandlerActivity : AppCompatActivity(){

    private lateinit var downloadHandlerThread: DownloadHandlerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvmactivity)

        downloadHandlerThread = DownloadHandlerThread("DownloadFileUsing-HandlerThread")
        downloadHandlerThread.start()

        //Lets post task to handler Thread to download files
        downloadHandlerThread.handler.post{
            val message =  downloadHandlerThread.handler.obtainMessage()
            message.obj = "https://example.com/file1.zip"
            downloadHandlerThread.handler.sendMessage(message)
        }

        //We can download multiple files , but one after the another
        //These operations will done in sequential order

        downloadHandlerThread.handler.post{
            val message = downloadHandlerThread.handler.obtainMessage()
            message.obj = "https://example.com/file2.zip"
            downloadHandlerThread.handler.sendMessage(message)
        }


    }
}