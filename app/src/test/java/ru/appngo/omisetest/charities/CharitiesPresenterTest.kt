package ru.appngo.omisetest.charities

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.appngo.omisetest.charities.data.CharityResponse
import ru.appngo.omisetest.network.NetworkDataSource
import ru.appngo.omisetest.testCharityList

private val testCharityResponse = CharityResponse(1, testCharityList)

class CharitiesPresenterTest {

    private lateinit var presenter: CharitiesPresenter
    private val view: CharitiesContract.View = mock()
    private val dataSource: NetworkDataSource = mock()

    @Before
    fun setUp() {
        presenter = CharitiesPresenter(dataSource)
    }

    @Test
    fun `confirm view shows list of charities on success request`() = runBlocking {
        setupGetCharities()

        presenter.onStart(view)

        verify(view).showProgress()
        verify(view).showCharitiesList(testCharityList)
        verify(view).dismissProgress()
    }

    @Test
    fun `on charity click navigate to donations`() = runBlocking {
        setupGetCharities()

        val position = 0
        presenter.onStart(view)
        presenter.onCharityClick(position)

        verify(view).navigateToDonations(testCharityList[position])
    }

    private suspend fun setupGetCharities() {
        whenever(dataSource.getCharities()).thenReturn(testCharityResponse)
    }
}

