package ru.appngo.omisetest.network

import retrofit2.http.GET
import ru.appngo.omisetest.charities.data.CharityResponse

interface OmiseService {

    @GET("/chakritw/tamboon-api/1.0.0/charities")
    suspend fun getCharities(): CharityResponse
}
