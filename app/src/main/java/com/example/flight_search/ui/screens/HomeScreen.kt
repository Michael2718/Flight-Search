package com.example.flight_search.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flight_search.R
import com.example.flight_search.data.Airport
import com.example.flight_search.data.FlightPair
import com.example.flight_search.ui.theme.FlightSearchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = stringResource(R.string.app_name),
////                        color = MaterialTheme.colorScheme.secondary,
//                        fontWeight = FontWeight.Bold,
//                        style = MaterialTheme.typography.headlineSmall
//                    )
//                },
//                actions = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Search,
//                            contentDescription = stringResource(R.string.search)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
//                    navigationIconContentColor = MaterialTheme.colorScheme.surface,
//                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
//                )
//            )
            CenterAlignedTopAppBar(
                title = {
                    SearchBar(
                        query = "",
                        onQueryChange = {},
                        onSearch = {},
                        active = false,
                        onActiveChange = {},
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_medium)),
//                            .fillMaxWidth(),
                        placeholder = { Text(text = "Search flights") },
                        leadingIcon = {
                            Icon(painter = painterResource(R.drawable.baseline_flight_takeoff_24), contentDescription = null)
                        }
                    ) {}
                },
                modifier = Modifier,
//                navigationIcon = {
//                    Icon(painter = painterResource(R.drawable.baseline_flight_24), contentDescription = null)
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
//                    navigationIconContentColor = MaterialTheme.colorScheme.surface,
//                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
//                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
//                SearchBar(
//                    query = "",
//                    onQueryChange = {},
//                    onSearch = {},
//                    active = false,
//                    onActiveChange = {},
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    placeholder = { Text(text = "Search flights") },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Filled.Search,
//                            contentDescription = stringResource(R.string.search)
//                        )
//                    }
//                ) {}
                Text(
                    text = "Saved flights",
                    modifier.padding(dimensionResource(R.dimen.padding_medium)),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                FlightCard(
                    departure = Airport(
                        1,
                        "OPO",
                        "Francisco Sá Carneiro Airport",
                        5053134
                    ),
                    destination = Airport(
                        2,
                        "ARN",
                        "Stockholm Arlanda Airport",
                        7494765
                    ),
                    modifier = Modifier.padding(
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
                FlightCard(
                    departure = Airport(
                        1,
                        "OPO",
                        "Francisco Sá Carneiro Airport",
                        5053134
                    ),
                    destination = Airport(
                        2,
                        "ARN",
                        "Stockholm Arlanda Airport",
                        7494765
                    )
                )
            }
        }
    }
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