package com.example.navigationtest.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtest.domain.models.RouteRequest
import com.example.navigationtest.domain.usecase.GetDirectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.maplibre.geojson.Point
import javax.inject.Inject

@HiltViewModel
class DirectionViewModel @Inject constructor(
    private val getDirectionsUseCase: GetDirectionsUseCase
) : ViewModel() {

    private val _routePoints = MutableLiveData<List<Point>>()
    val routePoints: LiveData<List<Point>> = _routePoints

    fun fetchRoute(origin: String, destination: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val direction = getDirectionsUseCase(RouteRequest(origin, destination))
            _routePoints.postValue(direction.points)
        }
    }
}