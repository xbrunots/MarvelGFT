package br.com.gft.marvel.feature.home.domain

import br.com.gft.marvel.feature.home.data.HomeRepository
import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import io.reactivex.Flowable

class HomeInteractor(private val repository: HomeRepository) : HomeContract.IInteractor {
    override fun getCharacters(): Flowable<CharacterResponse> {
        return repository.getCharacters()
    }
}