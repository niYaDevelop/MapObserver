package com.example.mapobserver.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapobserver.MapObserverViewModel
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
fun MapScreen(
    mapObserverViewModel: MapObserverViewModel = viewModel(factory = MapObserverViewModel.Factory)
){
    val uiState by mapObserverViewModel.uiState.collectAsState()
    val mapState = mapObserverViewModel.mapState

    MainMapView(
        mapState = mapState
    )
}


@Composable
fun MainMapView(
    mapState: MapState,
){
    MapUI(state = mapState)
}
