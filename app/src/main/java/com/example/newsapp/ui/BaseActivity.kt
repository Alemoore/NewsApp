package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    abstract val viewModel: NewsViewModel
}