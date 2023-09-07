package com.egardia.assessment.webservice

import com.egardia.assessment.model.Car
import retrofit2.Call
import retrofit2.http.GET

interface AssessmentService {
    @GET("/beckershoff/Egardia-Mobile-Development-Assessment/master/cars.json")
    fun getCars(): Call<List<Car>>
}