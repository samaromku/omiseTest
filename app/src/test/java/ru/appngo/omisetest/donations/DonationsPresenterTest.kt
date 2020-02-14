package ru.appngo.omisetest.donations

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.appngo.omisetest.donations.data.SendDonationResponse
import ru.appngo.omisetest.network.NetworkDataSource
import ru.appngo.omisetest.testDonation

private val successResponse = SendDonationResponse(true, "", "")
private val failureResponse = SendDonationResponse(true, "", "test_error_text")

class DonationsPresenterTest {

    private lateinit var presenter: DonationsPresenter
    private val view: DonationsContract.View = mock()
    private val dataSource: NetworkDataSource = mock()

    @Before
    fun setUp() {
        presenter = DonationsPresenter(dataSource)
        presenter.onStart(view)
    }

    @Test
    fun `on success response view navigates to next screen`() = runBlocking {
        whenever(dataSource.sendDonation(any())).thenReturn(successResponse)

        presenter.onSendButtonClick(testDonation)

        verify(view).showProgressIndicator()
        verify(view).navigateToSuccess()
        verify(view).hideProgressIndicator()
    }

    @Test
    fun `on error response view shows error`() = runBlocking {
        whenever(dataSource.sendDonation(any())).thenReturn(failureResponse)

        presenter.onSendButtonClick(testDonation)

        verify(view).showProgressIndicator()
        verify(view).showError(failureResponse.errorMessage)
        verify(view).hideProgressIndicator()
    }
}
