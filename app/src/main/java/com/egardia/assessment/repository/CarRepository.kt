package com.egardia.assessment.repository

import com.egardia.assessment.model.Car
import com.egardia.assessment.webservice.AssessmentService
import javax.inject.Inject

class CarRepository @Inject constructor (private val assessmentService: AssessmentService) {
    suspend fun getCars(result: (List<Car>) -> Unit) {
        assessmentService.getCars().body()?.let {
            result(it)
        } ?: run {
            println("Error fetching data")
        }
    }
}