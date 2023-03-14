package com.moudy.alshafie.Ui.Fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.moudy.alshafie.Data.DataSets.WordsDataset
import com.moudy.alshafie.Data.HomeRV
import com.moudy.alshafie.DataBase.UserDatabaseHelper
import com.moudy.alshafie.R
import com.moudy.alshafie.adapter.adapter
import com.moudy.alshafie.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myDbHelper: UserDatabaseHelper
    private lateinit var tts: TextToSpeech
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        context?.let {
            myDbHelper = UserDatabaseHelper(it)
        }
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
        addrv()
        addRandom()

        var username = ""
        val cursor = myDbHelper.readableDatabase.rawQuery("SELECT username FROM users", null)
        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(0)
            cursor.close()
        }
        binding.tvusername.text = username
        binding.randomtv.setOnClickListener {
            Thread.sleep(200) // Pause execution for 1 second
            binding.randomtv.text = WordsDataset.getNof()
        }
        binding.playbtn.setOnClickListener {


            val text = binding.randomtv.text.toString()
            if (text.isNotEmpty()) {
                val result = tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                if (result == TextToSpeech.ERROR) {
                    Log.e("TTS", "Failed to speak text")
                }
            }
        }

        return binding.root

    }




    private fun addrv() {
        val rvitems = listOf(
            HomeRV(
                "voice assistant will help you to learn new words in English",
                R.drawable.ic_illustration
            ),
            HomeRV(
                "voice assistant will help you to learn new words in English",
                R.drawable.ic_illustration
            ),
            HomeRV(
                "voice assistant will help you to learn new words in English",
                R.drawable.ic_illustration
            )
        )

        val data = ArrayList<HomeRV>()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homerv.layoutManager = layoutManager

        val adapter = adapter(data)

        binding.homerv.adapter = adapter(rvitems)
    }

    fun addRandom() {
        binding.randomtv.text = WordsDataset.getNof()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }
}



