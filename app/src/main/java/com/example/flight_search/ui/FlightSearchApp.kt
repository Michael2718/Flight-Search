package com.example.flight_search.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flight_search.ui.screens.home.HomeScreen
import com.example.flight_search.ui.screens.home.HomeViewModel

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlightSearchApp(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.factory)
) {
    HomeScreen(
        viewModel = viewModel,
        modifier = modifier
    )
}