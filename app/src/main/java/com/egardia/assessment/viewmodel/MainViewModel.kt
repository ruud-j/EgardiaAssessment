package com.egardia.assessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egardia.assessment.model.Car
import com.egardia.assessment.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val carRepository: CarRepository): ViewModel() {
    private val _cars: MutableLiveData<List<Car>> = MutableLiveData<List<Car>>()
    val cars : LiveData<List<Car>> get() = _cars

    init {
        viewModelScope.launch {
            carRepository.getCars {
                _cars.value = it
            }
        }
    }
}