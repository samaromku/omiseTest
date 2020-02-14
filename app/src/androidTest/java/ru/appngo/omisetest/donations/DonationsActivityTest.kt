package ru.appngo.omisetest.donations

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.appngo.omisetest.charities.data.Charity

private val testCharity = Charity(1, "testCharityName", "testLogo")

@RunWith(AndroidJUnit4::class)
class DonationsActivityTest {

    @get:Rule
    var rule: ActivityTestRule<DonationsActivity> = ActivityTestRule(DonationsActivity::class.java)

    private lateinit var donationsPage: DonationsPage

    @Before
    fun setup() {
        rule.launchActivity(DonationsActivity.createIntent(rule.activity, testCharity))
        donationsPage = DonationsPage()
    }

    @Test
    fun testLayout() {
        donationsPage.onDonationName()
                .check(matches(isDisplayed()))
                .check(matches(withText(testCharity.name)))
        donationsPage.onAccountNumber()
                .check(matches(isDisplayed()))
        donationsPage.onAmountToDonate()
                .check(matches(isDisplayed()))
        donationsPage.onDonateButton()
                .check(matches(isDisplayed()))
    }

    @Test
    fun afterEnteringTextInAccountItIsEmpty() {
        donationsPage.onAccountNumber()
                .perform(ViewActions.typeText("test text"))
        donationsPage.onAccountNumber()
                .check(matches(isDisplayed()))
                .check(matches(withText("")))
    }
}
