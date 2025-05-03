package com.rishi.newsapp.di.component

import android.content.Context
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.data.api.Networkservice
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.di.ApplicationContext
import com.rishi.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MVVMApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkservice(): Networkservice

    fun getTopheadlineRepository(): TopheadlineRepository
}