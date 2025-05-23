package com.rishi.newsapp.ui.SourcePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SourceViewModel @Inject constructor(val repository: TopheadlineRepository) : ViewModel() {

    private val mutable_source = MutableStateFlow<UiState<List<SourceListTable>>>(UiState.Loading)
    val uistate_source: StateFlow<UiState<List<SourceListTable>>> = mutable_source

    init {
        fetchSource()
    }

    fun fetchSource() {
        viewModelScope.launch {
            repository.getSourceslits()
                .flowOn(Dispatchers.IO)
                .catch {
                mutable_source.value = UiState.Error(it.toString())
            }.collect {
                mutable_source.value = UiState.Success(it)
            }
        }
    }

}