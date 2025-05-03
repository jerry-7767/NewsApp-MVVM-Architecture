package com.rishi.newsapp.ui.HomePage

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

class HomeViewModel(val repository: TopheadlineRepository) : ViewModel() {

    private val mutable_uiState_article = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uistate_article_: StateFlow<UiState<List<Article>>> = mutable_uiState_article

    private val mutable_uiState_sources = MutableStateFlow<UiState<List<SourcesList>>>(UiState.Loading)
    val uistate_sources_: StateFlow<UiState<List<SourcesList>>> = mutable_uiState_sources

    private val mutable_uiState_count = MutableStateFlow<UiState<TopHeadlineResponse>>(UiState.Loading)
    val uistate_count: StateFlow<UiState<TopHeadlineResponse>> = mutable_uiState_count

    private val mutable_newslanguage_count = MutableStateFlow<UiState<TopHeadlineResponse>>(UiState.Loading)
    val uistate_newslanguage_count: StateFlow<UiState<TopHeadlineResponse>> = mutable_newslanguage_count

    private val mutable_uiState_newsbysources = MutableStateFlow<UiState<TopHeadlineResponse>>(UiState.Loading)
    val uiState_newsbysources: StateFlow<UiState<TopHeadlineResponse>> = mutable_uiState_newsbysources

    private val mutable_uiState_countrylist = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val uistate_country_: StateFlow<UiState<List<Country>>> = mutable_uiState_countrylist

    init {
        fetchnews()
    }

    private fun fetchnews() {
        viewModelScope.launch {
            repository.getSourceslits()
                .catch {
                    mutable_uiState_sources.value = UiState.Error(it.toString())
                }.collect{
                    mutable_uiState_sources.value = UiState.Success(it)
                }
           /* repository.getCountrylist()
                .catch {
                    mutable_uiState_countrylist.value = UiState.Error(it.toString())
                }.collect{
                    mutable_uiState_countrylist.value = UiState.Success(it)
                }*/
        }
    }

    fun fetchNewsbyCountry(id: String) {
        mutable_uiState_count.value = UiState.Loading
        viewModelScope.launch {
            repository.getTotalresult(id)
                .catch {
                    mutable_uiState_count.value = UiState.Error(it.toString())
                }.collect {
                    mutable_uiState_count.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsbyLanguage(id: String) {
        mutable_newslanguage_count.value = UiState.Loading
        viewModelScope.launch {
            repository.getNewsby_Language(id)
                .catch {
                    mutable_newslanguage_count.value = UiState.Error(it.toString())
                }.collect {
                    mutable_newslanguage_count.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsbySources(source: String) {
        mutable_uiState_newsbysources.value = UiState.Loading
        viewModelScope.launch {
            repository.gettopheadlinesbySources(source)
                .catch {
                    mutable_uiState_newsbysources.value = UiState.Error(it.toString())
                }.collect {
                    mutable_uiState_newsbysources.value = UiState.Success(it)
                }
        }
    }
}