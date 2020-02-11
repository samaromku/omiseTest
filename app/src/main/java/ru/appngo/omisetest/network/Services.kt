package ru.appngo.omisetest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://virtserver.swaggerhub.com/"

object Services {

    private val service: OmiseService

    init {
        service = createService()
    }

    private fun createService(): OmiseService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OmiseService::class.java)
    }

    fun getOmiseService(): OmiseService {
        return service
    }
}
