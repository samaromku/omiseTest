package ru.appngo.omisetest.donations

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import ru.appngo.omisetest.R

class DonationsPage {

    fun onDonationName(): ViewInteraction = onView(withId(R.id.donation_name))

    fun onAccountNumber(): ViewInteraction = onView(withId(R.id.account_number))

    fun onAmountToDonate(): ViewInteraction = onView(withId(R.id.amount_to_donate))

    fun onDonateButton(): ViewInteraction = onView(withId(R.id.donate_button))
}
