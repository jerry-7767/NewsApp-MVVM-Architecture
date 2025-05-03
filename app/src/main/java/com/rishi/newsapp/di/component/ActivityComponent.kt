package com.rishi.newsapp.di.component

import com.rishi.newsapp.di.ActivityScope
import com.rishi.newsapp.di.module.ActivityModule
import com.rishi.newsapp.ui.HomeActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: HomeActivity)
}