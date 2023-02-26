package com.moudy.alshafie.Ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.moudy.alshafie.MainActivity
import com.moudy.alshafie.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
     private lateinit var binding:ActivityWelcomeBinding
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

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user:String = intent.getStringExtra("username").toString()
        binding.welcome.setText("Hi $user, Welcome")
    binding.btn.setOnClickListener(){
                startActivity(Intent(this, MainActivity::class.java))

    }
    }
}