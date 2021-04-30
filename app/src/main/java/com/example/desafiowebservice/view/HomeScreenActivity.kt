package com.example.desafiowebservice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservice.R
import com.example.desafiowebservice.RecyclerScrollListener
import com.example.desafiowebservice.model.Comics
import com.example.desafiowebservice.view.adapter.ComicsAdapter
import com.example.desafiowebservice.viewmodel.HomeScreenViewModel

class HomeScreenActivity : AppCompatActivity() {

    private val nextPageLoading: ProgressBar by lazy { findViewById(R.id.next_loading_home) }
    private val firstPageLoading: ProgressBar by lazy { findViewById(R.id.first_loading_home) }
    private val viewModelHomeScreen by lazy { ViewModelProvider(this).get(HomeScreenViewModel::class.java) }
    private var comics = mutableListOf<Comics>()
    private lateinit var comicAdapter: ComicsAdapter

    private val recyclerScrollListener by lazy {
        RecyclerScrollListener {
            viewModelHomeScreen.requestNextPage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        val recycler = findViewById<RecyclerView>(R.id.recycler_view_home)

        comicAdapter = ComicsAdapter()
        recycler.adapter = comicAdapter
        val layoutManager = GridLayoutManager(this, 3)
        recycler.layoutManager = layoutManager

        recycler.addOnScrollListener(recyclerScrollListener)

        viewModelHomeScreen.getAllComics()
        viewModelHomeScreen.listComics.observe(this, Observer { comics ->
            setRequestingNextPage()
            comicAdapter.addComics(comics)
        })

        viewModelHomeScreen.nextPageLoading.observe(this) {
            if (it) {
                nextPageLoading.visibility = View.VISIBLE
            } else {
                nextPageLoading.visibility = View.GONE
            }
        }

        viewModelHomeScreen.firstPageLoading.observe(this) {
            if (it) {
                firstPageLoading.visibility = View.VISIBLE
            } else {
                firstPageLoading.visibility = View.GONE
            }
        }
    }

    private fun setRequestingNextPage() {
        recyclerScrollListener.requesting = false
    }
}