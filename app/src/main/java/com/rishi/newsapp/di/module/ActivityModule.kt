package com.rishi.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.di.ActivityContext
import com.rishi.newsapp.ui.HomePage.HomeViewModel
import com.rishi.newsapp.ui.HomePage.NewsAdapter
import com.rishi.newsapp.ui.HomePage.NewsViewpagerAdapter
import com.rishi.newsapp.ui.base.ViewmodelproviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

}