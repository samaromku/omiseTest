package ru.appngo.omisetest.charities

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.appngo.omisetest.charities.data.Charity
import ru.appngo.omisetest.charities.data.CharityResponse
import ru.appngo.omisetest.network.OmiseService

class CharitiesPresenterTest {

    private lateinit var presenter: CharitiesPresenter
    private val view: CharitiesContract.View = mock()

    @Before
    fun setUp() {
        presenter = CharitiesPresenter(DummyService())
    }

    @Test
    fun `confirm view shows list of charities on success request`() = runBlocking {
        presenter.onStart(view)

        verify(view).showProgress()
        verify(view).showCharitiesList(testCharityList)
        verify(view).dismissProgress()
    }

    @Test
    fun `on charity click navigate to donations`() = runBlocking {
        val position = 0
        presenter.onStart(view)
        presenter.onCharityClick(position)

        verify(view).navigateToDonations(testCharityList[position])
    }
}

val testCharityList = listOf(Charity(1, "testName", "testLogo"))

class DummyService : OmiseService {
    override suspend fun getCharities(): CharityResponse {
        return CharityResponse(testCharityList.size, testCharityList)
    }
}
