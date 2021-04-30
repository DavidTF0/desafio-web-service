package com.example.desafiowebservice.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.desafiowebservice.R
import com.example.desafiowebservice.model.Comics
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class DetailsActivity : AppCompatActivity() {

    private val buttonBack: ImageButton by lazy { findViewById(R.id.img_btn_back) }
    private val comicBackDrop: ImageView by lazy { findViewById(R.id.backdrop_details) }
    private val comicImgCard: CardView by lazy { findViewById(R.id.cardview_hq_details) }
    private val comicImg: ImageView by lazy { findViewById(R.id.imgview_hq_details) }
    private val comicTitle: TextView by lazy { findViewById(R.id.txt_title_details) }
    private val comicDetail: TextView by lazy { findViewById(R.id.txt_description_details) }
    private val comicPublished: TextView by lazy { findViewById(R.id.txt_published_details) }
    private val comicPrice: TextView by lazy { findViewById(R.id.txt_price_details) }
    private val comicPage: TextView by lazy { findViewById(R.id.txt_page_details) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        details()
    }

    private fun details() {

        buttonBack.setOnClickListener {
            onBackPressed()
        }

        val comic = intent.extras

        if (comic != null) {

            val comics = comic.getSerializable("COMIC") as Comics
            val imagePathThumbnail = comics.thumbnail.path.replace("http", "https") + ".jpg"
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(comics.dates[0].date)
            val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(date)

            Picasso.get().load(imagePathThumbnail).into(comicImg)
            Picasso.get().load(imagePathThumbnail).into(comicBackDrop)

            comicTitle.text = comics.title
            comicDetail.text = comics.description
            comicPublished.text = "Published: $formattedDate"
            comicPrice.text = "Price: ${comics.prices[0].price}"
            comicPage.text = "Pages: ${comics.pageCount}"

            comicImgCard.setOnClickListener {
                val intentImageDetails = Intent(it.context, ImageDetailsActivity::class.java)

                intentImageDetails.putExtra("COMIC_IMAGE", imagePathThumbnail)

                it.context.startActivity(intentImageDetails)
            }

        }
    }
}