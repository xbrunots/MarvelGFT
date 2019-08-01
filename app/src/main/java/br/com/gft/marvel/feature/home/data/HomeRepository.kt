package br.com.gft.marvel.feature.home.data

import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import br.com.gft.marvel.feature.home.domain.HomeContract
import io.reactivex.Flowable

class HomeRepository(private val dataSource: HomeDataSource) : HomeContract.IRepository {
    override fun getCharacters(): Flowable<CharacterResponse> {
        return dataSource.getRemoteCharacters()
    }
}