package br.com.gft.marvel.feature.home.domain

import br.com.gft.marvel.feature.home.data.service.model.CharacterItem
import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import br.com.gft.marvel.feature.home.presentation.HomePresenter
import br.com.gft.marvel.platform.base.BaseMvpPresenter
import br.com.gft.marvel.platform.base.BaseMvpView
import io.reactivex.Flowable

interface HomeContract {
    interface IView : BaseMvpView {
        fun invokeView(items: CharacterResponse)
        fun invokeAdapter(list: List<CharacterItem>)
        fun showError(error: String)
    }

    interface IPresenter : BaseMvpPresenter {

        fun filter(text: String)
    }

    interface IInteractor {
        fun getCharacters(): Flowable<CharacterResponse>
    }

    interface IRepository {
        fun getCharacters(): Flowable<CharacterResponse>
    }

    interface IDataSource {
        fun getLocalCharacters(): Flowable<CharacterResponse>
        fun getRemoteCharacters(): Flowable<CharacterResponse>
    }
}