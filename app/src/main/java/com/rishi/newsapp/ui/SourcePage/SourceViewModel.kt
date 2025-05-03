package com.rishi.newsapp.ui.SourcePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.data.model.Source
import me.amitshekhar.newsapp.data.model.SourcesList

class SourceViewModel(val repository: TopheadlineRepository):ViewModel() {

    private val mutable_source = MutableStateFlow<UiState<List<SourcesList>>>(UiState.Loading)
    val uistate_source: StateFlow<UiState<List<SourcesList>>> = mutable_source

    init {
        fetchSource()
    }

    fun fetchSource() {
        viewModelScope.launch {
            repository.getSourceslits().catch {
                mutable_source.value = UiState.Error(it.toString())
            }.collect{
                mutable_source.value = UiState.Success(it)
            }
        }
    }

}