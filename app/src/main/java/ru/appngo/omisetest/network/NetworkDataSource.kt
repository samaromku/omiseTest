package ru.appngo.omisetest.network

import retrofit2.Response
import ru.appngo.omisetest.charities.data.CharityResponse
import ru.appngo.omisetest.donations.data.Donation
import ru.appngo.omisetest.donations.data.SendDonationResponse

interface NetworkDataSource {
    suspend fun getCharities(): CharityResponse
    suspend fun sendDonation(donation: Donation): SendDonationResponse
}

class NetworkDataSourceImpl(
    private val service: OmiseService,
    private val networkStateChecker: NetworkStateChecker
) : NetworkDataSource {

    override suspend fun getCharities(): CharityResponse {
        checkNetwork()
        return service.getCharities()
                .checkResponseBody()
                .handleHttpCodes()
                .body()!!
    }

    override suspend fun sendDonation(donation: Donation): SendDonationResponse {
        checkNetwork()
        return service.sendDonation(donation)
                .checkResponseBody()
                .handleHttpCodes()
                .body()!!
    }

    private fun checkNetwork() {
        if (!networkStateChecker.isNetworkAvailable()) {
            throw IllegalStateException("Network is not available")
        }
    }

    private fun <T> Response<T>.checkResponseBody(): Response<T> {
        if (this.body() == null) {
            throw IllegalStateException("Server response is null")
        }
        return this
    }

    private fun <T> Response<T>.handleHttpCodes(): Response<T> {
        when (this.code()) {
            200 -> {
                //do nothing, code is OK
            }
            401 -> throw IllegalStateException("user not authorized")
            //etc. all codes: https://restfulapi.net/http-status-codes/
        }
        return this
    }
}
