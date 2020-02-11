package ru.appngo.omisetest.charities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.appngo.omisetest.R
import ru.appngo.omisetest.charities.data.Charity

class CharitiesActivity : AppCompatActivity(), CharitiesContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charities)
    }

    override fun showCharitiesList(charities: List<Charity>) {

    }

    override fun navigateToDonations(charity: Charity) {

    }
}
