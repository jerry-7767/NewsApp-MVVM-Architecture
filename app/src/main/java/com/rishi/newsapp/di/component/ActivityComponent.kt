package com.rishi.newsapp.di.component

import com.rishi.newsapp.MainActivity
import com.rishi.newsapp.di.ActivityScope
import com.rishi.newsapp.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}