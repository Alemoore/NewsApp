package com.example.newsapp.repository

import com.example.newsapp.api.NewsAPIService
import com.example.newsapp.db.ArticleDatabase
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val db: ArticleDatabase,
    private val api: NewsAPIService
) {

    suspend fun getBreakingNews(countyCode: String, pageNumber: Int) = api.getBreakingNews(countyCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) = api.searchForNews(searchQuery, pageNumber)
}