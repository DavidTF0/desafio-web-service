package com.example.desafiowebservice.network

import com.example.desafiowebservice.model.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointApi {

    @GET("v1/public/comics?")
    suspend fun getResponseAllComics(
        @Query("offset") offset: Int?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("apikey") apiKey : String?): ComicsResponse
}