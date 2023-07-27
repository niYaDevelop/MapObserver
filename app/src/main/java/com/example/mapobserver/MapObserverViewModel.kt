package com.example.mapobserver

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mapobserver.providers.makeTileStreamProvider
import com.example.mapobserver.ui.MapObserverStateUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.enableRotation
import ovh.plrapps.mapcompose.ui.state.MapState


class MapObserverViewModel(
    private val application: MapObserverApplication
): ViewModel() {


    private val _uiState = MutableStateFlow(MapObserverStateUI())
    val uiState: StateFlow<MapObserverStateUI> = _uiState.asStateFlow()
    private val tileStreamProvider = makeTileStreamProvider(application)


    val mapState: MapState by mutableStateOf(
        MapState(4, 4096, 4096).apply {
            addLayer(tileStreamProvider)
            enableRotation()
        }
    )

    init{
        viewModelScope.launch {
            findRandomObjects()
        }
    }

    private fun findRandomObjects(){

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MapObserverApplication)
                MapObserverViewModel(application)
            }
        }
    }
}