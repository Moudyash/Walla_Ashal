package com.moudy.alshafie.Ui

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.moudy.alshafie.MainActivity
import com.moudy.alshafie.R
import com.moudy.alshafie.databinding.ActivityPhotoBinding
import java.util.*

class PhotoActivity : AppCompatActivity() {
     private lateinit var binding:ActivityPhotoBinding
    private lateinit var tts: TextToSpeech
    private val REQUEST_CODE_SPEECH_INPUT = 100
    private var word: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

            // Enable the immersive mode.
            // This will hide the navigation bar and the status bar until the user swipes up or down.
            // You can remove this line if you don't want to enable immersive mode.
            systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }





        word = intent.getStringExtra("word").toString()
        binding.texxt.setText("Saying $word in English")
        binding.playimage.setOnClickListener(){

            if (word.isNotEmpty()) {
                val result = tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
                if (result == TextToSpeech.ERROR) {
                    Log.e("TTS", "Failed to speak text")
                }
            }
        }

        binding.back.setOnClickListener(){
            startActivity(Intent(this@PhotoActivity, MainActivity::class.java))

        }
        binding.speakbtn.setOnClickListener(){
            promptSpeechInput()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...")

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Sorry, speech recognition is not supported on this device.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val txtResult: String // define as class-level variable

                    txtResult = result!![0]
                    if (txtResult.equals(word, ignoreCase = true)){
                        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show()

                    }else                         Toast.makeText(this, "faield", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}