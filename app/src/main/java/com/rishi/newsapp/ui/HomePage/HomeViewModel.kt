package com.rishi.newsapp.ui.HomePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.Local.entity.ArticleTable
import com.rishi.newsapp.data.Local.entity.SourceListTable
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
class HomeViewModel @Inject constructor(
    val repository: TopheadlineRepository
) : ViewModel() {

    private val mutable_uiState_sources =
        MutableStateFlow<UiState<List<SourceListTable>>>(UiState.Initial)
    val uistate_sources_: StateFlow<UiState<List<SourceListTable>>> = mutable_uiState_sources

    private val mutable_news = MutableStateFlow<UiState<List<ArticleTable>>>(UiState.Initial)
    val news_list: StateFlow<UiState<List<ArticleTable>>> = mutable_news

    /* init {
         fetchnews()
     }*/

    fun fetchSourcelist() {
        viewModelScope.launch {
            mutable_uiState_sources.value = UiState.Loading
            repository.getSourceslits()
                .flowOn(Dispatchers.IO)
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
               repository.getNewsByCountry(id)
                   .flowOn(Dispatchers.IO)
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
                   .flowOn(Dispatchers.IO)
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
                   .flowOn(Dispatchers.IO)
                   .catch {
                       mutable_news.value = UiState.Error(it.toString())
                   }.collect {
                       mutable_news.value = UiState.Success(it)
                   }
           }
       }

    //fetch data from db
    fun fetchSourcelistFromDB() {
        viewModelScope.launch {
            mutable_uiState_sources.value = UiState.Loading
            repository.getSourcelistFromDB()
                .flowOn(Dispatchers.IO)
                .catch {
                    mutable_uiState_sources.value = UiState.Error(it.toString())
                }.collect {
                    mutable_uiState_sources.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsOffline() {
        mutable_news.value = UiState.Loading
        viewModelScope.launch {
            repository.getNewsByCountryOffline()
                .flowOn(Dispatchers.IO)
                .catch {
                    mutable_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_news.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsFromDB() {
        mutable_news.value = UiState.Loading
        viewModelScope.launch {
            repository.getNewsFromDB()
                .flowOn(Dispatchers.IO)
                .catch {
                    mutable_news.value = UiState.Error(it.toString())
                }.collect {
                    mutable_news.value = UiState.Success(it)
                }
        }
    }
}