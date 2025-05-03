package com.rishi.newsapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseVersion

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl