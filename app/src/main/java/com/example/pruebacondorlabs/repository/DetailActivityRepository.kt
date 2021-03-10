package com.example.pruebacondorlabs.repository

import com.example.pruebacondorlabs.api.ApiInterface
import com.example.pruebacondorlabs.db.model.Results
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DetailActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getEventsByTeamId(id: String): Single<Response<Results>> {
        return apiInterface.getEventsByTeamId(id)
    }
}