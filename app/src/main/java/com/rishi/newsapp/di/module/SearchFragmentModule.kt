package com.rishi.newsapp.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.di.FragmentContext
import com.rishi.newsapp.ui.HomePage.HomeFragment
import com.rishi.newsapp.ui.HomePage.HomeViewModel
import com.rishi.newsapp.ui.HomePage.NewsAdapter
import com.rishi.newsapp.ui.HomePage.NewsViewpagerAdapter
import com.rishi.newsapp.ui.SearchPage.SearchFragment
import com.rishi.newsapp.ui.SearchPage.SearchViewModel
import com.rishi.newsapp.ui.base.ViewmodelproviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class SearchFragmentModule(private val fragment: SearchFragment) {

    @FragmentContext
    @Provides
    fun provideContext() = fragment.requireContext()

    @Provides
    fun provideHomeviewModel(repository: TopheadlineRepository): SearchViewModel {
        return ViewModelProvider(fragment,
            ViewmodelproviderFactory(SearchViewModel::class) {
                SearchViewModel(repository)
            })[SearchViewModel::class.java]
    }

    @Provides
    fun provideNewslistAdapter() = NewsAdapter(ArrayList())

}