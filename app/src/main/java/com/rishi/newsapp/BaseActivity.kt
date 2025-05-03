package com.rishi.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rishi.newsapp.di.component.DaggerActivityComponent
import com.rishi.newsapp.di.module.ActivityModule

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
      /*  DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .build().inject(
                this
            )*/
        super.onCreate(savedInstanceState)
    }
}