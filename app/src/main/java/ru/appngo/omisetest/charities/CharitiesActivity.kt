package ru.appngo.omisetest.charities

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.appngo.omisetest.R
import ru.appngo.omisetest.charities.data.Charity
import ru.appngo.omisetest.network.Services

class CharitiesActivity : AppCompatActivity(), CharitiesContract.View {

    private val presenter: CharitiesPresenter = CharitiesPresenter(Services.getOmiseService())
    private val dialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charities)
        GlobalScope.launch(context = Dispatchers.IO) {
            presenter.onStart(this@CharitiesActivity)
        }
    }

    override suspend fun showCharitiesList(charities: List<Charity>) = withContext(Main) {

    }

    override fun navigateToDonations(charity: Charity) {

    }

    override suspend fun showProgress() = withContext(Main) {
        dialog.show()
    }

    override suspend fun dismissProgress() = withContext(Main) {
        dialog.dismiss()
    }

    override suspend fun showError(error: String) = withContext(Main) {
        Toast.makeText(this@CharitiesActivity, error, Toast.LENGTH_SHORT).show()
    }
}
