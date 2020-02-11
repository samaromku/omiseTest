package ru.appngo.omisetest.network

import retrofit2.http.GET

interface OmiseService {

    @GET("/chakritw/tamboon-api/1.0.0/charities")
    fun getCharities()
}
