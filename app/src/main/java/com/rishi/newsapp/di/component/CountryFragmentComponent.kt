package com.rishi.newsapp.di.component

import com.rishi.newsapp.di.FragmentScope
import com.rishi.newsapp.di.module.CountryFragmentModule
import com.rishi.newsapp.ui.CountryPage.CountryFragment
import dagger.Component


@FragmentScope
@Component(modules = [CountryFragmentModule::class], dependencies = [ApplicationComponent::class])
interface CountryFragmentComponent {
    fun inject(countryFragment: CountryFragment)
}