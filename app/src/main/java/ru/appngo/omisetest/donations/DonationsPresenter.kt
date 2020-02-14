package ru.appngo.omisetest.donations

import ru.appngo.omisetest.donations.data.Donation
import ru.appngo.omisetest.network.OmiseService

class DonationsPresenter(
    private val service: OmiseService
) : DonationsContract.Presenter {

    private lateinit var view: DonationsContract.View

    override fun onStart(view: DonationsContract.View) {
        this.view = view
    }

    override suspend fun onSendButtonClick(donation: Donation) {
        view.showProgressIndicator()
        try {
            val data = service.sendDonation(donation)
            if (data.success) {
                if (data.errorMessage.isBlank()) {
                    view.navigateToSuccess()
                } else {
                    view.showError(data.errorMessage)
                }
            }
        } catch (exc: Exception) {
            view.showError("Unexpected error message: ${exc.message.toString()}")
        }
        view.hideProgressIndicator()
    }
}
