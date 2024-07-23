package com.singlepoint.androidkotlin.threads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.net.URL
import java.util.concurrent.Executors

/***
 * Executor Service
 *
 * 1.Works directly with threads
 * 2.Easier to manage thread pools and execute task *asynchronously*
 * 3.Its is a part of java.util.concurrent package
 * 4.Well suited to perform background task in android application.
 *
 *  UseCase :
 *   ***Imagine you need to download multiple file in parallel without blocking the Main thread.***
 *   Using Executor Service , You can manage pool of threads to handle these downloads efficently
 *
 *
 *  Benefits
 *  Effective thread pool management
 *  Task Queueing - Automatically Queues task , if all threads are busy, ensuring tasks are executed as soon as threads become available.
 *  LifeCycleManagement - It provides methods to start execute and shutdown the thread pool
 *
 *  Consideration:
 *   Exception Handling
 *   Lifecycle Management - Avoid issues like task are still running after the components(Activity/Fragment) are destroyed
 *   Resource Management - proper shutdown of services to release resources and avoid memory leaks
 * **/


class DownloadTaskForExecutor(private val fileUrl: String, private val outputFileName : String) : Runnable {
    override fun run() {
     try {
            val url = URL(fileUrl)
            val connection = url.openConnection()
            connection.connect()

            val input = BufferedInputStream(url.openStream())
            val output = FileOutputStream(outputFileName)

            val data = ByteArray(1024)
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                output.write(data, 0, count)
            }

            output.flush()
            output.close()
            input.close()

            println("Downloaded: $outputFileName")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


class MainActivityForExecutorService: AppCompatActivity(){

    private val executorService = Executors.newFixedThreadPool(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fileUrls = listOf(
            "https://example.com/file1.zip",
            "https://example.com/file2.zip",
            "https://example.com/file3.zip"
        )

        for((index, url) in fileUrls.withIndex()){
            val task  = DownloadTaskForExecutor(url, "file${index + 1}.zip")
            executorService.execute(task)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }
}