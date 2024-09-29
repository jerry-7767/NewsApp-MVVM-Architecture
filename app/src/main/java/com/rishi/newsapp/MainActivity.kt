package com.rishi.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rishi.newsapp.di.component.DaggerActivityComponent
import com.rishi.newsapp.di.module.ActivityModule
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainviewModel: MainviewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent.builder().activityModule(ActivityModule())
            .build().inject(
                this
            )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}