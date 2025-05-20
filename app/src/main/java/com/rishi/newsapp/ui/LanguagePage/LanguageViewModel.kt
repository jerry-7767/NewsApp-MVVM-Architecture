package com.rishi.newsapp.ui.LanguagePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.model.Language
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(val repository: TopheadlineRepository) : ViewModel() {
    val mutable_language = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)
    val language_list: StateFlow<UiState<List<Language>>> = mutable_language

    fun getLanguagelist() {
        mutable_language.value = UiState.Loading
        viewModelScope.launch {
            repository.getLanguageList().catch {
                mutable_language.value = UiState.Error(it.toString())
            }.collect {
                mutable_language.value = UiState.Success(it)
            }
        }
    }
}