package ru.appngo.omisetest.network

import android.content.Context
import android.net.ConnectivityManager


class NetworkStateChecker(private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
