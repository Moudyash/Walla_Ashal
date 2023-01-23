package com.moudy.alshafie.Worker.recevier;

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

import com.moudy.alshafie.Utills.Notifications

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        Notifications.addNotification(applicationContext)
        return Result.success()
    }
}