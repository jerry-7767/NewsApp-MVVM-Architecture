package com.rishi.newsapp

import android.app.Application
import com.rishi.newsapp.di.component.ApplicationComponent
import com.rishi.newsapp.di.component.DaggerApplicationComponent
import com.rishi.newsapp.di.module.ApplicationModule
import javax.inject.Inject


class MVVMApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}