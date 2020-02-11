package ru.appngo.omisetest.charities

import ru.appngo.omisetest.charities.data.Charity

interface CharitiesContract {

    interface View {
        fun showCharitiesList(charities: List<Charity>)
        fun navigateToDonations(charity: Charity)
    }

    interface Presenter {
        fun onStart()
        fun onCharityClick(position: Int)
    }
}
