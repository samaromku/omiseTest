package ru.appngo.omisetest.donations.data

import com.google.gson.annotations.SerializedName

data class SendDonationResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("error_code")
    val errorCode: String,
    @SerializedName("error_message")
    val errorMessage: String
)
