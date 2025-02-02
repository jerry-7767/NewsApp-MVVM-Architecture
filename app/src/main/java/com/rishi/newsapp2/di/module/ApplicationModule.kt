package com.rishi.newsapp2.di.module

import android.content.Context
import com.rishi.newsapp2.NewsApplication
import com.rishi.newsapp2.di.ApplicationContext
import com.rishi.newsapp2.di.BaseUrl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val newsApplication: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context{
        return newsApplication
    }

    @BaseUrl
    @Provides
    fun provideBaseurl(): String = "https://newsapi.org/v2/"

}