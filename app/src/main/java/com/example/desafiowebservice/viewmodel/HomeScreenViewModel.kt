package com.example.desafiowebservice.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiowebservice.model.Comics
import com.example.desafiowebservice.model.Data
import com.example.desafiowebservice.repository.RepositoryMarvel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

    val listComics = MutableLiveData<List<Comics>>()
    private val repository = RepositoryMarvel()
    val nextPageLoading by lazy { MutableLiveData<Boolean>() }
    val firstPageLoading by lazy { MutableLiveData<Boolean>() }
    private var nextPage = 0

    fun getAllComics() = CoroutineScope(IO).launch {
        try {
            firstPageLoading.postValue(true)
            repository.getAllMarvelComicsService(nextPage).let {
                val quadrinhos = it.data.comic
                listComics.postValue(quadrinhos)
            }
        } catch (error: Throwable){

            Log.e("Error", "Problema de Configuration $error")

        } finally {
            firstPageLoading.postValue(false)
        }
    }

    fun requestNextPage() = CoroutineScope(IO).launch {
        try {
            nextPageLoading.postValue(true)
            repository.getAllMarvelComicsService(nextPage).let {
                updateNextPage()
                listComics.postValue(it.data.comic)
            }
        } catch (error: Throwable){
            Log.e("Error", "Problema de Configuration $error")

        } finally {
            nextPageLoading.postValue(false)
        }
    }

    private fun updateNextPage(){
        nextPage += 20
    }

}

