package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
        tableName = "articles"
)
data class Article(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var author: String,
        var content: String,
        var description: String,
        var publishedAt: String,
        var source: Source,
        var title: String,
        var url: String,
        var urlToImage: String
)