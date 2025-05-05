package com.rishi.newsapp.di.module

import android.content.Context
import com.rishi.newsapp.ui.CountryPage.CountryAdapter
import com.rishi.newsapp.ui.HomePage.NewsAdapter
import com.rishi.newsapp.ui.HomePage.NewsViewpagerAdapter
import com.rishi.newsapp.ui.LanguagePage.LanguageAdapter
import com.rishi.newsapp.ui.SourcePage.SourceAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
class FragmentModulee {

    @Provides
    fun provideNewssliderAdapter() = NewsViewpagerAdapter(ArrayList())

    @Provides
    fun provideNewslistAdapter() = NewsAdapter(ArrayList())

    @Provides
    fun provideCountryAdapter() =
        CountryAdapter(ArrayList())

    @Provides
    fun provideLanguageAdapter() =
        LanguageAdapter(ArrayList())

    @Provides
    fun provideSourceAdapter() =
        SourceAdapter(ArrayList())

}