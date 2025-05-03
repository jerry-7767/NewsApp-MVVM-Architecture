package com.rishi.newsapp.di.component

import com.rishi.newsapp.di.FragmentScope
import com.rishi.newsapp.di.module.HomeFragmentModule
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.ui.HomePage.HomeFragment
import dagger.Component

@FragmentScope
@Component(modules = [HomeFragmentModule::class], dependencies = [ApplicationComponent::class])
interface HomeFragmentComponent {
    fun inject(homeFragment: HomeFragment)
}