package com.example.newsapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.newsapp.getOrAwaitValue
import com.example.newsapp.models.Article
import com.example.newsapp.models.Source
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArticleDatabase
    private lateinit var dao: ArticleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        dao = database.getArticleDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArticle() = runBlockingTest {
        val article = Article(
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

        dao.insertOrUpdate(article)
        val allArticles = dao.getAllArticles().getOrAwaitValue()

        assertThat(allArticles[0]).isEqualTo(article)
    }

    @Test
    fun deleteArticle() = runBlockingTest {
        val article = Article(
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

        dao.insertOrUpdate(article)

        dao.deleteArticle(article)

        val allArticles = dao.getAllArticles().getOrAwaitValue()

        assertThat(allArticles).doesNotContain(article)

    }

}