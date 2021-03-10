package com.example.pruebacondorlabs.api

import com.example.pruebacondorlabs.db.model.Detail
import com.example.pruebacondorlabs.db.model.SearchBySite
import com.example.pruebacondorlabs.db.model.Site
import com.example.pruebacondorlabs.util.IDS
import com.example.pruebacondorlabs.util.ITEMS
import com.example.pruebacondorlabs.util.SITES
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET(SITES)
    fun getSites(
    ): Single<Response<ArrayList<Site>>>

    @GET
    fun getSearchBySite(
        @Url searchBySite: String,
    ): Single<Response<SearchBySite>>

    @GET(ITEMS)
    fun getDetailIdBySearch(
        @Query(IDS) idBySearch: String,
    ): Single<Response<ArrayList<Detail>>>
}