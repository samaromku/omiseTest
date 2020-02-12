package ru.appngo.omisetest.donations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.appngo.omisetest.R
import ru.appngo.omisetest.charities.data.Charity

private const val DONATION_NAME_EXTRA = "charity"

class DonationsActivity : AppCompatActivity() {

    companion object {

        fun createIntent(context: Context, charity: Charity) =
                Intent(context, DonationsActivity::class.java).apply {
                    putExtra(DONATION_NAME_EXTRA, charity.name)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)
        val name = getDonationName()
    }

    private fun getDonationName() = intent.getStringExtra(DONATION_NAME_EXTRA)
}
