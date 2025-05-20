package com.rishi.newsapp.ui.CountryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.data.repository.TopheadlineRepository
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(val repository: TopheadlineRepository) : ViewModel() {
    val mutable_country = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val country_list: StateFlow<UiState<List<Country>>> = mutable_country

    fun getCountrylist() {
        mutable_country.value = UiState.Loading
        viewModelScope.launch {
            repository.getCountrylist().catch {
                mutable_country.value = UiState.Error(it.toString())
            }.collect {
                mutable_country.value = UiState.Success(it)
            }
        }
    }
}