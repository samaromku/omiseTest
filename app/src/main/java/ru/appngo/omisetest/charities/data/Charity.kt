package ru.appngo.omisetest.charities.data

import com.google.gson.annotations.SerializedName

data class CharityResponse(
    @SerializedName("total")
    private val total: Int,
    @SerializedName("data")
    private val data: List<Charity>
)

data class Charity(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("logo_url")
    private val logoUrl: String
)
