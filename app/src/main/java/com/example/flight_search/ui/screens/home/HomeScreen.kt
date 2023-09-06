package com.example.flight_search.ui.screens.home

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flight_search.R
import com.example.flight_search.data.Airport
import com.example.flight_search.ui.theme.FlightSearchTheme
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val context: Context
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchTopAppBar(
                query = uiState.query,
                suggestionsFlow = viewModel.getSuggestions(uiState.query),
                onQueryChange = {
                    viewModel.updateQuery(it)
                },
                onSearch = {
                    viewModel.updateQuery(it)
                    viewModel.updateDeparture(it)
                    viewModel.isSearching(false)
                    keyboardController?.hide()
                },
                isSearching = uiState.isSearching,
                onActiveChange = {
                    viewModel.isSearching(it)
                },
                onBack = {
                    viewModel.isSearching(false)
                    keyboardController?.hide()
                },
                onClear = {
                    viewModel.updateQuery("")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { innerPadding ->
        HomeScreenContent(
            query = uiState.query,
            currentDeparture = uiState.currentDeparture,
            destinationsFlow = viewModel.getDestinations(uiState.currentDeparture.iataCode), // TODO: ????????
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    query: String,
    suggestionsFlow: Flow<List<Airport>>,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    isSearching: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onBack: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    val suggestionsList by suggestionsFlow.collectAsState(initial = emptyList())

    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isSearching,
        onActiveChange = onActiveChange,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_medium)),
        placeholder = { Text(text = stringResource(R.string.search_here)) },
        leadingIcon = {
            if (isSearching) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(R.drawable.baseline_flight_takeoff_24),
                    contentDescription = stringResource(R.string.flight_icon)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = onClear) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.clear)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = suggestionsList) { suggestion ->
                ListItem(
                    headlineContent = {
                        Text(suggestion.iataCode, fontWeight = FontWeight.Bold)
                    },
                    supportingContent = {
                        Text(text = suggestion.name)
                    },
                    modifier = Modifier
                        .clickable {
                            onSearch(suggestion.name)
                        }
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    query: String,
    currentDeparture: Airport,
    destinationsFlow: Flow<List<Airport>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = if (query.isEmpty()) {
                stringResource(R.string.favourite_routes)
            } else {
                stringResource(R.string.flights_from, currentDeparture.iataCode)
            },
            modifier.padding(dimensionResource(R.dimen.padding_medium)),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        val savedDestinations = listOf(
            Airport(
                2,
                "ARN",
                "Stockholm Arlanda Airport",
                7494765
            ),
            Airport(
                1,
                "OPO",
                "Francisco Sá Carneiro Airport",
                5053134
            )
        )

        val destinationsList by destinationsFlow.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (query.isEmpty()) {
                items(items = savedDestinations) { destination ->
                    FlightCard(
                        departure = destination,
                        destination = destination,
                        modifier = Modifier.padding(
                            bottom = dimensionResource(R.dimen.padding_medium)
                        )
                    )
                }
            } else {
                items(items = destinationsList) { destination ->
                    FlightCard(
                        departure = currentDeparture,
                        destination = destination,
                        modifier = Modifier.padding(
                            bottom = dimensionResource(R.dimen.padding_medium)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SavedFlights(
    modifier: Modifier = Modifier
) {

}

@Composable
fun FlightCard(
    departure: Airport,
    destination: Airport,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = departure.iataCode, fontWeight = FontWeight.Bold)
                Text(text = departure.name)

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.save_to_favourite)
                )

                Text(text = destination.iataCode, fontWeight = FontWeight.Bold)
                Text(text = destination.name)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.save_to_favourite)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightCardPreview() {
    FlightSearchTheme {
//        FlightCard(
//
//        )
    }
}