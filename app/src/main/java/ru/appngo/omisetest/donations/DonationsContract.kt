package ru.appngo.omisetest.donations

import ru.appngo.omisetest.donations.data.Donation

interface DonationsContract {
    interface View {
        suspend fun showProgressIndicator()
        suspend fun hideProgressIndicator()
        suspend fun navigateToSuccess()
        suspend fun showError(error: String)
    }

    interface Presenter {
        fun onStart(view: View)
        suspend fun onSendButtonClick(donation: Donation)
    }
}
