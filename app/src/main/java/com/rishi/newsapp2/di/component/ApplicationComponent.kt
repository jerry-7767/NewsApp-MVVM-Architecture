package com.rishi.newsapp2.di.component

import com.rishi.newsapp2.NewsApplication
import com.rishi.newsapp2.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(newsApplication: NewsApplication)
}