package com.rishi.newsapp.ui.SourcePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.model.SourcesList
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(val repository: TopheadlineRepository) : ViewModel() {

    private val mutable_source = MutableStateFlow<UiState<List<SourcesList>>>(UiState.Loading)
    val uistate_source: StateFlow<UiState<List<SourcesList>>> = mutable_source

    fun fetchSource() {
        viewModelScope.launch {
            repository.getSourceslits().catch {
                mutable_source.value = UiState.Error(it.toString())
            }.collect {
                mutable_source.value = UiState.Success(it)
            }
        }
    }

}