package com.egardia.assessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egardia.assessment.model.Car
import com.egardia.assessment.repository.CarRepository

class MainViewModel : ViewModel() {
    private val _cars: MutableLiveData<List<Car>> = MutableLiveData<List<Car>>()
    val cars : LiveData<List<Car>> get() = _cars

    init {
        CarRepository.getCars {
            _cars.value = it
        }
    }
}