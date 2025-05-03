package com.rishi.newsapp.ui.base

import android.os.Parcelable.Creator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

class ViewmodelproviderFactory<T : ViewModel>(
    private val KClass: KClass<T>, private val creator: () -> T
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown Class name")
    }
}