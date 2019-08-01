package br.com.gft.marvel.feature.home

import br.com.gft.marvel.feature.home.data.service.model.*
import br.com.gft.marvel.feature.home.domain.HomeContract
import br.com.gft.marvel.feature.home.presentation.HomePresenter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class HomePresenterTest {
    private val view = mock<HomeContract.IView>()
    private var interactor = mock<HomeContract.IInteractor>()
    private lateinit var presenter: HomePresenter
    private var testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(view)
    }

    @Test
    fun attachView_success() {
        Thread.currentThread().interrupt()
        val mockedReturn = CharacterResponse(
            200, "ok", "tagtagtga", ResultData(
                0, 20, 2000, 20,
                listOf(
                    CharacterItem(
                        10293,
                        "Spider Man",
                        "Um heroi legal",
                        Thumbnail(
                            "https://cdn2us.denofgeek.com/sites/denofgeekus/files/styles/main_wide/public/2019/07/spider-man-far-from-home-ending-explained",
                            "jpg"
                        ), "http://marvel.com",
                        listOf(
                            UrlItem("wiki", "https://www.marvel.com")
                        )
                    )
                )
            )
        )

        doReturn(Flowable.just(mockedReturn))
            .`when`(interactor)
            .getCharacters()

        presenter.attachView()
        testScheduler.triggerActions()
        verify(view).showLoading()
    }

    @Test
    fun attachView_throwable() {
        Thread.currentThread().interrupt()
        val genericException = Exception("Não foi possível buscar o heroi")
        Mockito.`when`(interactor.getCharacters()).thenReturn(Flowable.error(genericException))
        presenter.attachView()
        verify(view).showLoading()
        testScheduler.triggerActions()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.detachView()
    }
}