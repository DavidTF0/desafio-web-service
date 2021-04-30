package com.example.desafiowebservice.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservice.R

class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cardViewComic: CardView = view.findViewById(R.id.cardview_hq_home)
    val comicImage: ImageView = view.findViewById(R.id.imgview_hq_recycler)
    val comicTitle: TextView = view.findViewById(R.id.txt_comic_title)
}