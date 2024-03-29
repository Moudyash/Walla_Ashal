package com.moudy.alshafie.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
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
import com.moudy.alshafie.DataBase.FavoriteDatabaseHelper
import com.moudy.alshafie.R
import com.moudy.alshafie.Ui.PhotoActivity
import java.util.*

class FavoriteAdapter(private val cursor: Cursor) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(), TextToSpeech.OnInitListener {
    private lateinit var context: Context
    private lateinit var tts: TextToSpeech
    private val favoriteDatabaseHelper = FavoriteDatabaseHelper(context)
    private lateinit var english:String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemfavorite, parent, false)
        tts = TextToSpeech(parent.context, this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cursor = favoriteDatabaseHelper.readableDatabase.rawQuery("SELECT word FROM favorite", null)
        if (cursor != null && cursor.moveToFirst()) {
             english = cursor.getString(1)
            val arabic = cursor.getString(2)
            val ar_en = cursor.getString(3)

            holder.arabic.text = arabic
            holder.ar_en.text = ar_en
            holder.english.text = english
            cursor.close()
        }




        holder.beginnercard.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.popup
            )
        )
        holder.imageButton.setOnClickListener {
            val text: String = english
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            if (tts.isSpeaking) {
                holder.imageButton.setBackgroundResource(R.drawable.ic_pause)

            } else if (!tts.isSpeaking) holder.imageButton.setBackgroundResource(R.drawable.ic_cplay)

        }
        holder.beginnercard.setOnClickListener {
            val intent = Intent(holder.itemView.context, PhotoActivity::class.java).putExtra(
                "word",
                holder.english.text
            )
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return cursor.count
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


