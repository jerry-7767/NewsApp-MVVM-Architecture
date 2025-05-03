package com.rishi.newsapp.di.module

import android.content.Context
import com.rishi.newsapp.di.FragmentContext
import com.rishi.newsapp.ui.CountryPage.CountryAdapter
import com.rishi.newsapp.ui.CountryPage.CountryFragment
import dagger.Module
import dagger.Provides

@Module
class CountryFragmentModule(private val fragment: CountryFragment) {

    @FragmentContext
    @Provides
    fun provideContext() = fragment.requireContext()

    @Provides
    fun provideCountryAdapter(@FragmentContext context: Context) = CountryAdapter(ArrayList(),context)
}