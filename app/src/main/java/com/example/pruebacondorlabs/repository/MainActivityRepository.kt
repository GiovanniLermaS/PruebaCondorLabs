package com.example.pruebacondorlabs.repository

import com.example.pruebacondorlabs.api.ApiInterface
import com.example.pruebacondorlabs.db.model.Teams
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getTeamsByLeague(league: String): Single<Response<Teams>> {
        return apiInterface.getTeamsByLeague(league)
    }
}