package com.moudy.alshafie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.moudy.alshafie.Data.HomeRV
import com.moudy.alshafie.R

class adapter(private val homelist: List<HomeRV>)  : RecyclerView.Adapter<adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemhome, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val list = homelist[position]
        holder.description.text=list.description
        holder.photo.setImageResource(list.photo)
holder.card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,R.anim.popup))


    }

    override fun getItemCount(): Int {
        return homelist.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val description: TextView =itemView.findViewById(R.id.itemtext)
        val photo: ImageView =itemView.findViewById(R.id.itemimageView)
        val card: CardView =itemView.findViewById(R.id.homecardview)

    }
}


