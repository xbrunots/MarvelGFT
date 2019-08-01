package br.com.gft.marvel.feature.home.presentation

import br.com.gft.data.source.local.cache
import br.com.gft.data.source.local.findCacheByModel
import br.com.gft.marvel.feature.home.data.HomeDataSource
import br.com.gft.marvel.feature.home.data.HomeRepository
import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import br.com.gft.marvel.feature.home.domain.HomeContract
import br.com.gft.marvel.feature.home.domain.HomeInteractor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(val view: HomeContract.IView) : HomeContract.IPresenter {

    private val ioScheduler: Scheduler = Schedulers.io()
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
    private val compositeDisposable = CompositeDisposable()
    private lateinit var interactor: HomeInteractor
    private lateinit var repository: HomeRepository

    override fun attachView() {
        initRepository()
        compositeDisposable.add(interactor.getCharacters()
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe({
                cache<CharacterResponse>(it)
                view.invokeView(it)
            }, {
                view.showError(it.message.toString())
            })
        )
    }

    private fun initRepository() {
        repository = HomeRepository(HomeDataSource())
        interactor = HomeInteractor(repository)
    }

    override fun filter(text: String) {
        findCacheByModel<CharacterResponse>().let {
            val response = it!!
            val newList = response.data.results.filter {
                it.name.contains(text, true)
            }
            view.invokeAdapter(newList)
        }
    }

    override fun detachView() {
        compositeDisposable.dispose()
    }
}