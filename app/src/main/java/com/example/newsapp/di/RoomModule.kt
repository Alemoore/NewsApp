package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.db.ArticleDao
import com.example.newsapp.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideArticleDB(application: Application): ArticleDatabase {
        return Room.databaseBuilder(
                application,
                ArticleDatabase::class.java,
                "SavedArticles")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao = articleDatabase.getArticleDao()
}