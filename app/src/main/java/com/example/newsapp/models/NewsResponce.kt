package com.example.newsapp.models

import com.example.newsapp.models.Article

data class NewsResponce(
        var articles: List<Article>,
        var status: String,
        var totalResults: Int
)