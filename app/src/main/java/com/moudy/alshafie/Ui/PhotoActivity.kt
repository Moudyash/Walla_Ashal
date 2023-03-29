package com.moudy.alshafie.Ui

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moudy.alshafie.Api.PixabayApiService
import com.moudy.alshafie.Api.PixabayImage
import com.moudy.alshafie.DataBase.FavoriteDatabaseHelper
import com.moudy.alshafie.MainActivity
import com.moudy.alshafie.R
import com.moudy.alshafie.databinding.ActivityPhotoBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class PhotoActivity : AppCompatActivity() {
     private lateinit var binding:ActivityPhotoBinding
    private lateinit var tts: TextToSpeech
    private val REQUEST_CODE_SPEECH_INPUT = 100
    private var word: String = ""
    private var wordar: String = ""
    private var wordae: String = ""

    private lateinit var recyclerView: RecyclerView
    private val favoriteDatabaseHelper = FavoriteDatabaseHelper(this)
//    private  val COLUMN_WORD = "word"
//    private  val COLUMN_ArWORD = "worda"
//    private  val COLUMN_AeWORD = "wordae"

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
        word = intent.getStringExtra("word").toString()
        binding.texxt.setText("Saying $word in English")
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
                    .build()
            )
            .build()

        val apiService = retrofit.create(PixabayApiService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = apiService.searchImagesrec("34302165-94635f5534cfb347509cf4a06",word)
            withContext(Dispatchers.Main) {
                recyclerView.adapter = ImageAdapter(response.hits)

            }

//            val dbfav = favoriteDatabaseHelper.readableDatabase
//            val cursor = dbfav.rawQuery("SELECT * FROM favorite WHERE word = ?", arrayOf(word))
//            val isFavorite = cursor.count > 0
//            cursor.close()
//            dbfav.close()
//
//            if (isFavorite) {
//                binding.favbtn.setBackgroundResource(R.drawable.ic_favoritefiled)
//            } else {
//                binding.favbtn.setBackgroundResource(R.drawable.ic_favorite)
//            }
//            binding.favbtn.setOnClickListener() {
//                val added = addOrRemoveFavorite(word)
//            }

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
    private class ImageAdapter(private val images: List<PixabayImage>) :
        RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

        class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.item_image_view)
            val cardView: CardView =itemView.findViewById(R.id.card)
            // val titleTextView: TextView = itemView.findViewById(R.id.item_text_view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_layout, parent, false)
            return ImageViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {



            val image = images[position]
            Picasso.get().load(image.webformatURL).into(holder.imageView)}
        //  holder.titleTextView.text = image.tags

        override fun getItemCount(): Int = images.size
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
//    fun deleteFavorite(word: String): Boolean {
//        val db = favoriteDatabaseHelper.writableDatabase
//        val selection = "$COLUMN_WORD = ?"
//        val selectionArgs = arrayOf(word)
//        val deletedRows = db.delete("favorite", selection, selectionArgs)
//        return deletedRows > 0
//    }
//    private fun addOrRemoveFavorite(word: String) {
//        val isFavorite = isFavorite(word)
//        if (isFavorite) {
//            // Remove the word from favorites
//            val deleted = deleteFavorite(word)
//            if (deleted) {
//                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
//                binding.favbtn.setBackgroundResource(R.drawable.ic_favorite)
//            } else {
//                Toast.makeText(this, "Error removing from favorites", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            // Add the word to favorites
//            val added = addFavorite(word, wordar , wordae)
//            if (added) {
//                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
//                binding.favbtn.setBackgroundResource(R.drawable.ic_favoritefiled)
//            } else {
//                Toast.makeText(this, "Error adding to favorites", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    fun addFavorite(word: String,worda:String,wordae:String): Boolean {
//        if (isFavorite(word)) {
//            return false
//        }
//        val db = favoriteDatabaseHelper.writableDatabase
//        val values = ContentValues().apply {
//            put(COLUMN_WORD, word)
//            put(COLUMN_ArWORD, worda)
//            put(COLUMN_AeWORD, wordae)
//
//        }
//        val result = db.insert("favorite", null, values)
//        return result != -1L
//    }
//    fun isFavorite(word: String): Boolean {
//        val db = favoriteDatabaseHelper.readableDatabase
//        val columns = arrayOf(COLUMN_WORD)
//        val selection = "$COLUMN_WORD = ?"
//        val selectionArgs = arrayOf(word)
//        val cursor = db.query(
//            "favorite",
//            columns,
//            selection,
//            selectionArgs,
//            null,
//            null,
//            null
//        )
//        val count = cursor.count
//        cursor.close()
//        return count > 0
//    }


}
