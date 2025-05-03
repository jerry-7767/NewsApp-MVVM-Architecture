package com.rishi.newsapp.di.component

import com.rishi.newsapp.di.FragmentScope
import com.rishi.newsapp.di.module.LanguageFragmentModule
import com.rishi.newsapp.ui.LanguagePage.LanguageFragment
import dagger.Component

@FragmentScope
@Component(modules = [LanguageFragmentModule::class], dependencies = [ApplicationComponent::class])
interface LanguageFragmentComponent {
    fun inject(languageFragment: LanguageFragment)
}