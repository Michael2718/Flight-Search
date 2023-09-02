package com.example.flight_search.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flight_search.FlightSearchApplication
import com.example.flight_search.data.Airport
import com.example.flight_search.data.FlightSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {

//    private val _uiState = MutableStateFlow(
//        HomeUiState()
//    )

//    val uiState: StateFlow<HomeUiState> = flightSearchRepository.getSuggestions("")
//        .map {
//            HomeUiState(suggestions = it)
//        }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//            initialValue = HomeUiState()
//        )

    private val _uiState = MutableStateFlow(
        HomeUiState(
            query = "",
            isSearching = false,
//            suggestions = getSuggestions("")
        )
    )

    val uiState: StateFlow<HomeUiState> = _uiState

//    fun searchFlights(query: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _uiState.update {
//                it.copy(
//                    flights = flightSearchRepository.getAllItems()
//                )
//            }
//        }
//    }

    fun getSuggestions(query: String) = flightSearchRepository.getSuggestions(query)

    fun searchFlights(query: String) = flightSearchRepository.getAllItems()

    fun updateQuery(query: String) {
        _uiState.update {
            it.copy(
                query = query
            )
        }
    }

    fun isSearching(isSearching: Boolean) {
        _uiState.update {
            it.copy(
                isSearching = isSearching
            )
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)
                val flightSearchRepository = application.container.flightSearchRepository
                HomeViewModel(flightSearchRepository)
            }
        }
    }
}

data class HomeUiState(
    val query: String,
    val isSearching: Boolean,
//    val suggestions: Flow<List<Airport>>
)