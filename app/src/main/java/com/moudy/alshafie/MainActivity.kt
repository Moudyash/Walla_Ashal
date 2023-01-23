package com.moudy.alshafie

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.moudy.alshafie.Worker.recevier.NotificationWorker
import com.moudy.alshafie.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val workManager = WorkManager.getInstance(this.application)

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireWorkerTodisplayNotification()
    }


    /**
     * display notification every 8 hours
     */
    @SuppressLint("EnqueueWork", "InvalidPeriodicWorkRequestInterval")
    private fun fireWorkerTodisplayNotification() {
        val workRequest =
            PeriodicWorkRequest.Builder(NotificationWorker::class.java, 2, TimeUnit.MINUTES)
                .setInitialDelay(8, TimeUnit.SECONDS)
                .build()
        workManager.enqueueUniquePeriodicWork(
            "worker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}