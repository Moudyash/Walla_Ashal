package com.moudy.alshafie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.moudy.alshafie.Worker.recevier.NotificationWorker
import com.moudy.alshafie.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val workManager = WorkManager.getInstance(this.application)

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

            // Enable the immersive mode.
            // This will hide the navigation bar and the status bar until the user swipes up or down.
            // You can remove this line if you don't want to enable immersive mode.
            systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        fireWorkerTodisplayNotification()

        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
    }

/**
 * display notification every 15 MINUTES
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

private fun loadFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.activity_main_nav_host_fragment, fragment)
    transaction.commit()
}
}




