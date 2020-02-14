package ru.appngo.omisetest.network

import android.content.Context

object NetworkDataSourceFactory {

    fun createDataSource(context: Context): NetworkDataSource =
            NetworkDataSourceImpl(Services.getOmiseService(), NetworkStateChecker(context))
}
