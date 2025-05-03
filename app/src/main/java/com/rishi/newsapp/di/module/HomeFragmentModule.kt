package com.rishi.newsapp.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.di.FragmentContext
import com.rishi.newsapp.ui.HomePage.HomeFragment
import com.rishi.newsapp.ui.HomePage.HomeViewModel
import com.rishi.newsapp.ui.HomePage.NewsAdapter
import com.rishi.newsapp.ui.HomePage.NewsViewpagerAdapter
import com.rishi.newsapp.ui.base.ViewmodelproviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class HomeFragmentModule(private val fragment: HomeFragment) {

    @FragmentContext
    @Provides
    fun provideContext() = fragment.requireContext()

    @Provides
    fun provideHomeviewModel(repository: TopheadlineRepository): HomeViewModel {
        return ViewModelProvider(fragment,
            ViewmodelproviderFactory(HomeViewModel::class) {
                HomeViewModel(repository)
            })[HomeViewModel::class.java]
    }

    @Provides
    fun provideNewssliderAdapter() = NewsViewpagerAdapter(ArrayList())

    @Provides
    fun provideNewslistAdapter() = NewsAdapter(ArrayList())

}