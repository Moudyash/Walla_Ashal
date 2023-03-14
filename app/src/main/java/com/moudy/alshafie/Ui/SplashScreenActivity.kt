package com.moudy.alshafie.Ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.moudy.alshafie.MainActivity
import com.moudy.alshafie.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
     private lateinit var binding:ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        decorView.systemUiVisibility = uiOptions

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val isChecked = sharedPreferences.getBoolean("checked", false)
        if (isChecked) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()          } else {
            startActivity(Intent(this, StepsActivity::class.java))
            finish()        }


         }
}