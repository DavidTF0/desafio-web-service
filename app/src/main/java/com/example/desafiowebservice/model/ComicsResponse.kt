package com.example.desafiowebservice.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicsResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    @SerializedName("data")
    val data: Data,
    val etag: String,
    val status: String
): Serializable