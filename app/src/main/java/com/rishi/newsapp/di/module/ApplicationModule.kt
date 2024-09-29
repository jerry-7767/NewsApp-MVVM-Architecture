package com.rishi.newsapp.di.module

import android.content.Context
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.data.FileHelper
import com.rishi.newsapp.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MVVMApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context{
        return application
    }

    @Singleton
    @Provides
    fun providefilehelper(): FileHelper{
        return FileHelper()
    }

}