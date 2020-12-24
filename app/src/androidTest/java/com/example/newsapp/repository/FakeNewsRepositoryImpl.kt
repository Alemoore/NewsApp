package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponce
import com.example.newsapp.util.Resource

class FakeNewsRepositoryImpl : NewsRepository {

    private val articles = mutableListOf<Article>()
    private val observableArticles = MutableLiveData<List<Article>>(articles)
    var shouldReturnNetworkError = false

    private fun refreshLiveData() {
        observableArticles.postValue(articles)
    }

    override suspend fun getBreakingNews(
        countyCode: String,
        pageNumber: Int
    ): Resource<NewsResponce> {
        return if (shouldReturnNetworkError) Resource.Error(message = "Fake error")
        else Resource.Success(NewsResponce(listOf(), "success", 200))
    }

    override suspend fun searchNews(
        searchQuery: String,
        pageNumber: Int
    ): Resource<NewsResponce> {
        return if (shouldReturnNetworkError) Resource.Error(message = "Fake error")
        else Resource.Success(NewsResponce(listOf(), "success", 200))
    }

    override suspend fun insertOrUpdate(article: Article): Long {
        articles.add(article)
        refreshLiveData()
        return article.id ?: Long.MAX_VALUE
    }

    override fun getSavedArticles(): LiveData<List<Article>> = observableArticles

    override suspend fun deleteArticle(article: Article) {
        articles.remove(article)
        refreshLiveData()
    }
}
