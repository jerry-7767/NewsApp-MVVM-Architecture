package com.rishi.newsapp.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class FragmentScope