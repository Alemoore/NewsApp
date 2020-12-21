package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponce
import com.example.newsapp.util.Resource
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countyCode: String, pageNumber: Int): Resource<NewsResponce>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Resource<NewsResponce>

    suspend fun insertOrUpdate(article: Article): Long

    fun getSavedArticles(): LiveData<List<Article>>

    suspend fun deleteArticle(article: Article)

}