package com.tromian.uanewsapp.models

import com.tromian.uanewsapp.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)