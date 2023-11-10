package com.egardia.assessment.webservice

import com.egardia.assessment.model.Car
import retrofit2.Response
import retrofit2.http.GET

interface AssessmentService {
    @GET("/beckershoff/Egardia-Mobile-Development-Assessment/master/cars.json")
    suspend fun getCars(): Response<List<Car>>
}