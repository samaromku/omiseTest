package ru.appngo.omisetest

import ru.appngo.omisetest.charities.data.Charity
import ru.appngo.omisetest.charities.data.CharityResponse
import ru.appngo.omisetest.donations.data.Donation
import ru.appngo.omisetest.donations.data.SendDonationResponse
import ru.appngo.omisetest.network.OmiseService

class DummyService : OmiseService {
    override suspend fun getCharities(): CharityResponse {
        return CharityResponse(testCharityList.size, testCharityList)
    }

    override suspend fun sendDonation(donation: Donation): SendDonationResponse {
        return SendDonationResponse(true, "", "")
    }
}

val testCharityList = listOf(Charity(1, "testName", "testLogo"))
val testDonation = Donation("testName", "testToken", 12345L)
