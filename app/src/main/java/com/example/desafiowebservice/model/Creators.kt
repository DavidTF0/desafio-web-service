package com.example.desafiowebservice.model

import java.io.Serializable

data class Creators(
    val available: String,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: String
): Serializable