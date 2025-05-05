package com.rishi.newsapp

import android.app.Application
import com.rishi.newsapp.di.module.ApplicationModule
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MVVMApplication: Application()