package com.example.flight_search.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flight_search.FlightSearchApplication
import com.example.flight_search.data.Airport
import com.example.flight_search.data.FlightSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val flightSearchRepository: FlightSearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState()
    )

    val uiState: StateFlow<HomeUiState> = _uiState

    fun getSuggestions(query: String) =
        flightSearchRepository.getSuggestions(query)

    fun updateDeparture(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            flightSearchRepository.getItemByQuery(query).collect { list ->
                _uiState.update {
                    it.copy(
                        currentDeparture = list.first()
                    )
                }
            }
        }
    }

    fun getDestinations(departureCode: String) =
        flightSearchRepository.getDestinations(departureCode)

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
    val query: String = "",
    val currentDeparture: Airport = Airport(
        id = -1,
        iataCode = "###",
        name = "Empty airport",
        passengers = 0
    ),
    val isSearching: Boolean = false,
)