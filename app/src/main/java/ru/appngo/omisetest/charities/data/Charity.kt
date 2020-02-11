package ru.appngo.omisetest.charities.data

import com.google.gson.annotations.SerializedName

data class CharityResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val data: List<Charity>
)

data class Charity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo_url")
    val logoUrl: String
)
