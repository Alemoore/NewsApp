package com.example.newsapp.di

import com.example.newsapp.api.NewsAPIService
import com.example.newsapp.db.ArticleDao
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        dao: ArticleDao,
        api: NewsAPIService
    ) = NewsRepositoryImpl(dao, api) as NewsRepository

}