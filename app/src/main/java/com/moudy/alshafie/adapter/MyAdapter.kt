package com.moudy.alshafie.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.moudy.alshafie.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MyAdapter     // Constructor
    (private val mData: List<String>) :

    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    // Provide a reference to the views for each data item
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        //        var mTextView: TextView
        var img: ImageView = v.findViewById(R.id.item_image_view)

        init {
//            mTextView = v.findViewById(R.id.item_text_view)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_layout, parent, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // holder.mTextView.text = mData[position]
         val imageHeights = intArrayOf(170, 250, 270, 260)
        val randomIndex = Random().nextInt(imageHeights.size)
        val height = imageHeights[randomIndex]

        // Set the height of the ImageView
        val layoutParams = holder.img.layoutParams
        layoutParams.height = height
        holder.img.layoutParams = layoutParams
    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mData.size
}

    }
