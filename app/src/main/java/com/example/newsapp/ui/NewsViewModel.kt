package com.example.newsapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponce
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel @ViewModelInject constructor(
    app: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(app) {
    val breakingNews: MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
    private var breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
    private var searchNewsPage = 1

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
                breakingNews.postValue(handleBreakingNewsResponse(response))
            } else {
                breakingNews.postValue(Resource.Error(message = "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> breakingNews.postValue(Resource.Error(message = "Network failure"))
                else -> breakingNews.postValue(Resource.Error(message = "Conversion exception"))
            }
        }

    }

    fun searchForNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.searchNews(searchQuery, searchNewsPage)
                searchNews.postValue(handleSearchNewsResponse(response))
            } else {
                searchNews.postValue(Resource.Error(message = "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchNews.postValue(Resource.Error(message = "Network failure"))
                else -> searchNews.postValue(Resource.Error(message = "Conversion exception"))
            }
        }


    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insertOrUpdate(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    fun getSavedArticles() = newsRepository.getSavedArticles()

    private fun handleBreakingNewsResponse(response: Response<NewsResponce>): Resource<NewsResponce> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(message = response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponce>): Resource<NewsResponce> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(message = response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<App>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= 23) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capability =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capability.hasTransport(TRANSPORT_WIFI) -> true
                capability.hasTransport(TRANSPORT_CELLULAR) -> true
                capability.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_ETHERNET -> true
                    TYPE_MOBILE -> true
                    else -> false
                }
            }
        }
        return false
    }
}