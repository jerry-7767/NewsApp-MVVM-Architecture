package com.rishi.newsapp.di.module

import android.content.Context
import com.rishi.newsapp.di.FragmentContext
import com.rishi.newsapp.ui.LanguagePage.LanguageAdapter
import com.rishi.newsapp.ui.LanguagePage.LanguageFragment
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class LanguageFragmentModule(private val fragment: LanguageFragment) {

    @FragmentContext
    @Provides
    fun provideContext() = fragment.requireContext()

    @Provides
    fun provideLanguageAdapter(@FragmentContext context: Context) = LanguageAdapter(ArrayList(),context)
}