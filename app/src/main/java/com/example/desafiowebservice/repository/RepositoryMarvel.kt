package com.example.desafiowebservice.repository

import com.example.desafiowebservice.MD5.Companion.md5
import com.example.desafiowebservice.model.ComicsResponse
import com.example.desafiowebservice.network.EndPointApi
import com.example.desafiowebservice.network.RetrofitInit

class RepositoryMarvel {

    private var url = "https://gateway.marvel.com/"

    private val PUBLIC_KEY: String? = "20683cce0145c0698a4be7a9e449adb8"
    private val PRIVATE_KEY: String? = "f7db67fb83c47912e15be2ad3f6692869a5ee269"
    private var ts: String? = (System.currentTimeMillis() / 1000).toString()
    var hash: String? = md5(ts + PRIVATE_KEY + PUBLIC_KEY)

    private var service = EndPointApi::class

    private val serviceMarvel = RetrofitInit(url).create(service)

    suspend fun getAllMarvelComicsService(offset: Int): ComicsResponse = serviceMarvel.getResponseAllComics(offset, ts, hash, PUBLIC_KEY)

}