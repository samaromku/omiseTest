package ru.appngo.omisetest.donations

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_donations.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.appngo.omisetest.R
import ru.appngo.omisetest.charities.data.Charity
import ru.appngo.omisetest.donations.data.Donation
import ru.appngo.omisetest.network.NetworkDataSourceFactory.createDataSource

private const val DONATION_NAME_EXTRA = "charity"

class DonationsActivity : AppCompatActivity(), DonationsContract.View {

    companion object {

        fun createIntent(context: Context, charity: Charity) =
                Intent(context, DonationsActivity::class.java).apply {
                    putExtra(DONATION_NAME_EXTRA, charity.name)
                }
    }

    private val presenter: DonationsContract.Presenter = DonationsPresenter(createDataSource(this))
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)
        presenter.onStart(this)
        donation_name.text = getDonationName()
        donate_button.setOnClickListener {
            GlobalScope.launch(context = Dispatchers.IO) {
                // TODO: can also add checks on empty fields, but don't think it worth it in the test project.
                presenter.onSendButtonClick(
                        Donation(name = donation_name.text.toString(),
                                token = account_number.text.toString(),
                                amount = amount_to_donate.text.toString().toLong()))
            }
        }
    }

    private fun getDonationName() = intent.getStringExtra(DONATION_NAME_EXTRA)

    override suspend fun showProgressIndicator() = withContext(Main) {
        progressDialog.show()
    }

    override suspend fun hideProgressIndicator() = withContext(Main) {
        progressDialog.dismiss()
    }

    override suspend fun navigateToSuccess() = withContext(Main) {
        // TODO: should be navigation to success screen,
        //  but service provides only error message, so cannot be implemented yet.
    }

    override suspend fun showError(error: String) = withContext(Main) {
        Toast.makeText(this@DonationsActivity, error, Toast.LENGTH_SHORT).show()
    }
}
