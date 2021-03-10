package com.example.pruebacondorlabs.api

import com.example.pruebacondorlabs.db.model.Teams
import com.example.pruebacondorlabs.util.ALL_TEAMS_BY_LEAGUE
import com.example.pruebacondorlabs.util.LEAGUE
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ALL_TEAMS_BY_LEAGUE)
    fun getTeamsByLeague(
        @Query(LEAGUE) league: String
    ): Single<Response<Teams>>
}