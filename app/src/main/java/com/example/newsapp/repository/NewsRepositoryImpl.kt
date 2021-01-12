package com.example.newsapp.repository

import com.example.newsapp.api.NewsAPIService
import com.example.newsapp.db.ArticleDao
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponce
import com.example.newsapp.util.Resource
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dao: ArticleDao,
    private val api: NewsAPIService
): NewsRepository {

    override suspend fun getBreakingNews(countyCode: String, pageNumber: Int): Resource<NewsResponce> {
        val response = api.getBreakingNews(countyCode, pageNumber)
        return handleNewsResponse(response)
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Resource<NewsResponce> {
        val response = api.searchForNews(searchQuery, pageNumber)
        return handleNewsResponse(response)
    }

    override suspend fun insertOrUpdate(article: Article) = dao.insertOrUpdate(article)

    override fun getSavedArticles() = dao.getAllArticles()

    override suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)

    private fun handleNewsResponse(response: Response<NewsResponce>): Resource<NewsResponce> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(message = response.message())
    }

}