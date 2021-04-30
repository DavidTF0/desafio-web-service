package com.example.desafiowebservice.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservice.R
import com.example.desafiowebservice.model.Comics
import com.example.desafiowebservice.view.DetailsActivity
import com.squareup.picasso.Picasso

class ComicsAdapter : RecyclerView.Adapter<ComicsViewHolder>() {
    private val comicsList =  mutableListOf<Comics>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic, parent, false)
        return ComicsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comicCardView = holder.cardViewComic
        val comics = comicsList.elementAt(position)
        val path = comics.thumbnail.path.replace("http", "https")+".jpg"
        Picasso.get().load(path).into(holder.comicImage)
        holder.comicTitle.text = comics.title

        comicCardView.setOnClickListener {
            val intentDetails = Intent(it.context, DetailsActivity::class.java)

            intentDetails.putExtra("COMIC", comicsList[position])

            it.context.startActivity(intentDetails)
        }
    }

    fun addComics(comics: List<Comics>){
        comicsList.addAll(comics)
        notifyDataSetChanged()
    }

}