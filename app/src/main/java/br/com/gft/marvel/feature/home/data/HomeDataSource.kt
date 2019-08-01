package br.com.gft.marvel.feature.home.data

import br.com.gft.data.client.RetrofitBuild.buildService
import br.com.gft.data.network.NetworkEndpoints
import br.com.gft.data.source.local.findCacheByModel
import br.com.gft.data.source.local.findCacheByRequest
import br.com.gft.marvel.feature.home.data.service.HomeService
import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import br.com.gft.marvel.feature.home.domain.HomeContract
import io.reactivex.Flowable


class HomeDataSource : HomeContract.IDataSource {
    override fun getLocalCharacters(): Flowable<CharacterResponse> {
        return Flowable.just(findCacheByModel<CharacterResponse>())
    }

    override fun getRemoteCharacters(): Flowable<CharacterResponse> {
        return buildService<HomeService>().getCharacters(
            br.com.gft.data.BuildConfig.MARVEL_API_KEY,
            br.com.gft.data.BuildConfig.MARVEL_HASH,
            br.com.gft.data.BuildConfig.MARVEL_TS
        )
    }
}

