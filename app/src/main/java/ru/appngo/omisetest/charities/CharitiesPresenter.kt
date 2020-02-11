package ru.appngo.omisetest.charities

import ru.appngo.omisetest.network.OmiseService

class CharitiesPresenter(
    private val omiseService: OmiseService
) : CharitiesContract.Presenter {

    lateinit var view: CharitiesContract.View

    override suspend fun onStart(view: CharitiesContract.View) {
        this.view = view
        view.showProgress()
        try {
            val data = omiseService.getCharities().data
            view.showCharitiesList(data)
        } catch (exc: Exception) {
            view.showError(exc.message.toString())
        }
        view.dismissProgress()
    }

    override fun onCharityClick(position: Int) {

    }
}
