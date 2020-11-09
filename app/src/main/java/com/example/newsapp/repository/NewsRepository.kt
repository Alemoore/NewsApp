package com.example.newsapp.repository

import com.example.newsapp.api.NewsAPIService
import com.example.newsapp.db.ArticleDao
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val dao: ArticleDao,
    private val api: NewsAPIService
) {

    suspend fun getBreakingNews(countyCode: String, pageNumber: Int) = api.getBreakingNews(countyCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) = api.searchForNews(searchQuery, pageNumber)

    suspend fun insertOrUpdate(article: Article) = dao.insertOrUpdate(article)

    fun getSavedArticles() = dao.getAllArticles()

    suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)


}