package com.egardia.assessment.repository

import com.egardia.assessment.model.Car
import com.egardia.assessment.webservice.Webservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CarRepository {
    fun getCars(result: (List<Car>) -> Unit) {
        Webservice.api.getCars().enqueue(object: Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                response.body()?.let { cars ->
                    result(cars)
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                println("Error: " + t.message)
            }
        })
    }
}