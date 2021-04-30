package com.example.desafiowebservice.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerializedName("results")
    val comic: List<Comics>,
    val total: Int
): Serializable