package ru.appngo.omisetest.charities

import ru.appngo.omisetest.network.OmiseService

class CharitiesPresenter(
    private val omiseService: OmiseService
) : CharitiesContract.Presenter {

    lateinit var view: CharitiesContract.View

    override suspend fun onStart(view: CharitiesContract.View) {
        this.view = view
        view.showCharitiesList(omiseService.getCharities().data)
    }

    override fun onCharityClick(position: Int) {

    }
}
