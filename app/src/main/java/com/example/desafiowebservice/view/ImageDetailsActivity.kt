package com.example.desafiowebservice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.example.desafiowebservice.R
import com.squareup.picasso.Picasso

class ImageDetailsActivity : AppCompatActivity() {

    private val closeButton: ImageButton by lazy { findViewById(R.id.img_btn_close) }
    private val comicImageDetail: ImageView by lazy { findViewById(R.id.imgview_comic_details) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_view_details)

        initImageDetails()
    }

    private fun initImageDetails(){

        closeButton.setOnClickListener {
            onBackPressed()
        }

        val imageComic = intent.extras

        val imagePath = imageComic?.getString("COMIC_IMAGE")

        Picasso.get().load(imagePath).into(comicImageDetail)
    }
}