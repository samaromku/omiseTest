package ru.appngo.omisetest.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.appngo.omisetest.charities.data.CharityResponse
import ru.appngo.omisetest.donations.data.Donation
import ru.appngo.omisetest.donations.data.SendDonationResponse

interface OmiseService {

    @GET("/chakritw/tamboon-api/1.0.0/charities")
    suspend fun getCharities(): Response<CharityResponse>

    @POST("/chakritw/tamboon-api/1.0.0/donations")
    suspend fun sendDonation(@Body donation: Donation): Response<SendDonationResponse>
}
