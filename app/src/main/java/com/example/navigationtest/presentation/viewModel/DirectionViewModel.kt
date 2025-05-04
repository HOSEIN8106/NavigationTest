package com.example.navigationtest.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationtest.domain.models.Direction
import com.example.navigationtest.domain.usecase.GetDirectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectionViewModel @Inject constructor(private val getDirectionsUseCase: GetDirectionsUseCase) : ViewModel() {
    private val _direction = MutableLiveData<Direction>()
    val direction: LiveData<Direction> = _direction

    fun fetchDirections(origin: String, destination: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _direction.value = getDirectionsUseCase(origin, destination)
        }
    }
}