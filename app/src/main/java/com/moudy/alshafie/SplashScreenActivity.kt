package com.moudy.alshafie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moudy.alshafie.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
     private lateinit var binding:ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startActivity(Intent(this, StepsActivity::class.java))
        finish()    }
}