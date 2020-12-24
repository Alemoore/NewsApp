package com.example.newsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.newsapp.getOrAwaitValue
import com.example.newsapp.models.Article
import com.example.newsapp.models.Source
import com.example.newsapp.repository.FakeNewsRepositoryImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NewsViewModelTest {
    private lateinit var viewModel: NewsViewModel
    private lateinit var article: Article

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel =
            NewsViewModel(ApplicationProvider.getApplicationContext(), FakeNewsRepositoryImpl())
        article = Article(
            1L,
            "author",
            "content",
            "description",
            "time",
            Source("name", "name"),
            "title",
            "url",
            "urlToImage"
        )
    }


    @Test
    fun saveArticleSuccessfully() {
        viewModel.saveArticle(article)
        val savedArticles = viewModel.getSavedArticles().getOrAwaitValue()
        assertThat(article).isEqualTo(savedArticles[0])
    }

    @Test
    fun deleteArticleSuccessfully(){
        viewModel.saveArticle(article)
        viewModel.deleteArticle(article)
        val savedArticles = viewModel.getSavedArticles().getOrAwaitValue()
        assertThat(savedArticles).isEmpty()
    }

}