package com.moudy.alshafie

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.moudy.alshafie.Ui.Fragments.FavoriteFragment
import com.moudy.alshafie.Ui.Fragments.HomeFragment
import com.moudy.alshafie.Ui.Fragments.LearningFragment
import com.moudy.alshafie.Ui.Fragments.SettingFragment
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
        navController= Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView,navController)
        fireWorkerTodisplayNotification()

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