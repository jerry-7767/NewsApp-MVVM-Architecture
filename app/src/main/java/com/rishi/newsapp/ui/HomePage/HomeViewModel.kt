package com.rishi.newsapp.ui.HomePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.model.SourcesList
import com.rishi.newsapp.data.model.TopHeadlineResponse
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: TopheadlineRepository) : ViewModel() {

    private val mutable_uiState_sources =
        MutableStateFlow<UiState<List<SourcesList>>>(UiState.Initial)
    val uistate_sources_: StateFlow<UiState<List<SourcesList>>> = mutable_uiState_sources

    private val mutable_news = MutableStateFlow<UiState<TopHeadlineResponse>>(UiState.Initial)
    val news_list: StateFlow<UiState<TopHeadlineResponse>> = mutable_news

   /* init {
        fetchnews()
    }*/

    fun fetchSourcelist() {
        viewModelScope.launch {
            mutable_uiState_sources.value = UiState.Loading
            repository.getSourceslits()
                .catch {
                    mutable_uiState_sources.value = UiState.Error(it.toString())
                }.collect {
                    mutable_uiState_sources.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsbyCountry(id: String) {
        mutable_news.value = UiState.Loading
        viewModelScope.launch {
            repository.getTotalresult(id)
                .catch {
                    mutable_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_news.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsbyLanguage(id: String) {
        mutable_news.value = UiState.Loading
        viewModelScope.launch {
            repository.getNewsby_Language(id)
                .catch {
                    mutable_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_news.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsbySources(source: String) {
        mutable_news.value = UiState.Loading
        viewModelScope.launch {
            repository.gettopheadlinesbySources(source)
                .catch {
                    mutable_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_news.value = UiState.Success(it)
                }
        }
    }
}