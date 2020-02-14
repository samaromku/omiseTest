package ru.appngo.omisetest.charities

import ru.appngo.omisetest.charities.data.Charity
import ru.appngo.omisetest.network.NetworkDataSource

class CharitiesPresenter(
    private val networkDataSource: NetworkDataSource
) : CharitiesContract.Presenter {

    lateinit var view: CharitiesContract.View
    lateinit var charities: List<Charity>

    override suspend fun onStart(view: CharitiesContract.View) {
        this.view = view
        view.showProgress()
        try {
            charities = networkDataSource.getCharities().data
            view.showCharitiesList(charities)
        } catch (exc: Exception) {
            view.showError(exc.message.toString())
        }
        view.dismissProgress()
    }

    override fun onCharityClick(position: Int) {
        view.navigateToDonations(charities[position])
    }
}
