package com.example.homework_26.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_26.data.common.ErrorType
import com.example.homework_26.data.common.Resource
import com.example.homework_26.domain.usecase.GetItemsUseCase
import com.example.homework_26.presentation.event.MainEvents
import com.example.homework_26.presentation.mapper.toPresentation
import com.example.homework_26.presentation.state.SearchStates
import com.example.homework_26.presentation.util.error_messages.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchStates())
    val searchState: SharedFlow<SearchStates> get() = _searchState

    fun onEvent(event: MainEvents) {
        when (event) {
            is MainEvents.Search -> event.query?.let { search(it) }
            is MainEvents.ClearSearch -> clearSearch()
            is MainEvents.UpdateErrorMessage -> updateErrorMessages(event.errorMessage)
        }
    }
    private fun search(query: String) {
        viewModelScope.launch {
            handleResource(getItemsUseCase.invoke(query = query)) { data ->
                _searchState.update { currentState ->
                    currentState.copy(items = data.map { it.toPresentation() })
                }
            }
        }
        Log.d("MainViewModel", "_searchState is updated")
    }

    private fun clearSearch() {
        _searchState.update { currentState ->
            currentState.copy(items = emptyList())
        }
    }

    private fun updateErrorMessages(errorMessage: ErrorType) {
        Log.d("MainViewModel", "_searchState is updated")
        val message = getErrorMessage(errorMessage)
        _searchState.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }

    private fun <T> handleResource(resourceFlow: Flow<Resource<T>>, handleSuccess: (T) -> Unit) {
        viewModelScope.launch {
            resourceFlow.collect { resource ->
                when (resource) {
                    is Resource.Success -> handleSuccess(resource.data)
                    is Resource.Error -> updateErrorMessages(resource.errorType)
                    is Resource.Loading -> _searchState.update { currentState ->
                        currentState.copy(isLoading = resource.loading)
                    }
                }
            }
        }
    }
}
