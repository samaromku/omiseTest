package ru.appngo.omisetest.network

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
    }

    override suspend fun sendDonation(donation: Donation): SendDonationResponse {
        checkNetwork()
        return service.sendDonation(donation)
    }

    private fun checkNetwork() {
        if (!networkStateChecker.isNetworkAvailable()) {
            throw IllegalStateException("Network is not available")
        }
    }
}
