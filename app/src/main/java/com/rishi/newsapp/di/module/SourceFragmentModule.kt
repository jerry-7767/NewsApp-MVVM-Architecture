package com.rishi.newsapp.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.di.FragmentContext
import com.rishi.newsapp.ui.LanguagePage.LanguageAdapter
import com.rishi.newsapp.ui.LanguagePage.LanguageFragment
import com.rishi.newsapp.ui.SourcePage.SourceAdapter
import com.rishi.newsapp.ui.SourcePage.SourceFragment
import com.rishi.newsapp.ui.SourcePage.SourceViewModel
import com.rishi.newsapp.ui.base.ViewmodelproviderFactory
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class SourceFragmentModule(private val fragment: SourceFragment) {

    @FragmentContext
    @Provides
    fun provideContext() = fragment.requireContext()

    @Provides
    fun provideSourceViewModel(repository: TopheadlineRepository): SourceViewModel {
        return ViewModelProvider(fragment, ViewmodelproviderFactory(SourceViewModel::class){
            SourceViewModel(repository)
        })[SourceViewModel::class.java]
    }

    @Provides
    fun provideSourceAdapter(@FragmentContext context: Context) = SourceAdapter(ArrayList(),context)
}