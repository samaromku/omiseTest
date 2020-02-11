package ru.appngo.omisetest.charities

import ru.appngo.omisetest.charities.data.Charity

interface CharitiesContract {

    interface View {
        fun showCharitiesList(charities: List<Charity>)
        fun navigateToDonations(charity: Charity)
    }

    interface Presenter {
        suspend fun onStart(view: View)
        fun onCharityClick(position: Int)
    }
}
