package com.rishi.newsapp.ui.SearchPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.data.model.TopHeadlineResponse
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.data.model.SourcesList

class SearchViewModel(val repository: TopheadlineRepository) : ViewModel() {

    private val mutable_search_news = MutableStateFlow<UiState<TopHeadlineResponse>>(UiState.Loading)
    val uistate_search_news: StateFlow<UiState<TopHeadlineResponse>> = mutable_search_news

    fun fetchNewsbysearch(query: String) {
        mutable_search_news.value = UiState.Loading
        viewModelScope.launch {
            repository.getSearchNews(query)
                .catch {
                    mutable_search_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_search_news.value = UiState.Success(it)
                }
        }
    }

}