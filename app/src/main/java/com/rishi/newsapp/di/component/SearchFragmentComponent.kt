package com.rishi.newsapp.di.component

import com.rishi.newsapp.di.FragmentScope
import com.rishi.newsapp.di.module.LanguageFragmentModule
import com.rishi.newsapp.di.module.SearchFragmentModule
import com.rishi.newsapp.di.module.SourceFragmentModule
import com.rishi.newsapp.ui.LanguagePage.LanguageFragment
import com.rishi.newsapp.ui.SearchPage.SearchFragment
import com.rishi.newsapp.ui.SourcePage.SourceFragment
import dagger.Component

@FragmentScope
@Component(modules = [SearchFragmentModule::class], dependencies = [ApplicationComponent::class])
interface SearchFragmentComponent {
    fun inject(searchFragment: SearchFragment)
}