package com.moudy.alshafie.Ui.Fragments.Levels

import android.app.Notification
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.moudy.alshafie.Data.Beginner
import com.moudy.alshafie.Data.DataSets.NotificationDataset
import com.moudy.alshafie.Data.DataSets.WordsDataset
import com.moudy.alshafie.Data.HomeRV
import com.moudy.alshafie.R
import com.moudy.alshafie.adapter.Beginneradapter
import com.moudy.alshafie.adapter.adapter
import com.moudy.alshafie.databinding.FragmentBeginnerBinding
import java.util.*
import kotlin.collections.ArrayList

class BeginnerFragment : Fragment() {
    private lateinit var binding: FragmentBeginnerBinding
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeginnerBinding.inflate(inflater, container, false)

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Set language
                val result = textToSpeech.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }



        val rvitems = mutableListOf<Beginner>()

        for (i in WordsDataset.BeginnerEN.indices) {
            val item = WordsDataset.BeginnerEN[i]
            val item2 = WordsDataset.BeginnerAr_En[i]
            val item3 = WordsDataset.BeginnerAR[i]
            rvitems.add(Beginner(item3,item2,item))
        }

        val data = ArrayList<Beginner>()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.beginnerrv.layoutManager = layoutManager

        val adapter = Beginneradapter(data)

        binding.beginnerrv.adapter = Beginneradapter(rvitems)









        return binding.root

    }


}



