package ru.appngo.omisetest.charities

import ru.appngo.omisetest.charities.data.Charity

interface CharitiesContract {

    interface View {
        suspend fun showProgress()
        suspend fun dismissProgress()
        suspend fun showCharitiesList(charities: List<Charity>)
        suspend fun showError(error: String)
        fun navigateToDonations(charity: Charity)
    }

    interface Presenter {
        suspend fun onStart(view: View)
        fun onCharityClick(position: Int)
    }
}
