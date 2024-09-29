package com.rishi.newsapp.di.module

import com.rishi.newsapp.MainviewModel
import com.rishi.newsapp.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun provideMainViewModel(): MainviewModel{
        return MainviewModel()
    }
}