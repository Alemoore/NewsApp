package com.example.newsapp

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.ui.BaseActivity
import com.example.newsapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity: BaseActivity(){
    override val viewModel: NewsViewModel by viewModels()
}