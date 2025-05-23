package com.rishi.newsapp.ui.SearchPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: TopheadlineRepository) : ViewModel() {

    private val mutable_search_news =
        MutableStateFlow<UiState<List<ArticleTable>>>(UiState.Loading)
    val uistate_search_news: StateFlow<UiState<List<ArticleTable>>> = mutable_search_news

    fun fetchNewsbysearch(query: String) {
        mutable_search_news.value = UiState.Loading
        viewModelScope.launch {
            repository.getSearchNews(query)
                .flowOn(Dispatchers.IO)
                .catch {
                    mutable_search_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_search_news.value = UiState.Success(it)
                }
        }
    }

}