package ru.appngo.omisetest.charities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_charities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.appngo.omisetest.R
import ru.appngo.omisetest.charities.data.Charity
import ru.appngo.omisetest.donations.DonationsActivity
import ru.appngo.omisetest.network.NetworkDataSourceFactory.createDataSource

/**
 * Represents charities.
 * Decides on which thread the code will be executed.
 */
class CharitiesActivity : AppCompatActivity(), CharitiesContract.View {

    private val presenter: CharitiesPresenter = CharitiesPresenter(createDataSource(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charities)
        GlobalScope.launch(context = Dispatchers.IO) {
            presenter.onStart(this@CharitiesActivity)
        }
    }

    override suspend fun showCharitiesList(charities: List<Charity>) = withContext(Main) {
        charity_list.adapter = CharitiesAdapter(charities) {
            presenter.onCharityClick(it)
        }
    }

    override fun navigateToDonations(charity: Charity) {
        startActivity(DonationsActivity.createIntent(this, charity))
    }

    override suspend fun showProgress() = withContext(Main) {
        progress_bar.visibility = View.VISIBLE
    }

    override suspend fun dismissProgress() = withContext(Main) {
        progress_bar.visibility = View.GONE
    }

    override suspend fun showError(error: String) = withContext(Main) {
        Toast.makeText(this@CharitiesActivity, error, Toast.LENGTH_SHORT).show()
    }
}
