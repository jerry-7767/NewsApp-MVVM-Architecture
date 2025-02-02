package com.rishi.newsapp2

import android.app.Application
import com.rishi.newsapp2.di.component.ApplicationComponent
import com.rishi.newsapp2.di.component.DaggerApplicationComponent
import com.rishi.newsapp2.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        injectdependencies()
    }

    private fun injectdependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}