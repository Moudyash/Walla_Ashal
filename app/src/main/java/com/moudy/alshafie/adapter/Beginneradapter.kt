package com.moudy.alshafie.adapter

import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.moudy.alshafie.Data.Beginner
import com.moudy.alshafie.R
import com.moudy.alshafie.Ui.PhotoActivity
import java.util.*

class Beginneradapter(private val homelist: List<Beginner>) :
    RecyclerView.Adapter<Beginneradapter.ViewHolder>(), TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itembeginner, parent, false)
        tts = TextToSpeech(parent.context, this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val list = homelist[position]
        holder.arabic.text = list.arabic
        holder.ar_en.text = list.ar_en
        holder.english.text = list.english
        holder.beginnercard.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.popup
            )
        )
        holder.imageButton.setOnClickListener {
            val text: String = list.english
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            if (tts.isSpeaking){
                holder.imageButton.setBackgroundResource(R.drawable.ic_pause)

            }else                 holder.imageButton.setBackgroundResource(R.drawable.ic_cplay)

        }
        holder.beginnercard.setOnClickListener(){
            val intent = Intent(holder.itemView.context, PhotoActivity::class.java)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return homelist.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val arabic: TextView = itemView.findViewById(R.id.arabictv)
        val ar_en: TextView = itemView.findViewById(R.id.ar_entv)
        val english: TextView = itemView.findViewById(R.id.englishtv)
        val beginnercard: CardView = itemView.findViewById(R.id.beginnercard)
        val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or not supported, inform the user
            }
        } else {
            // Initialization failed, inform the user
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        if (tts != null) {
            tts.stop()
            tts.shutdown()
        }
        super.onDetachedFromRecyclerView(recyclerView)
    }
}


