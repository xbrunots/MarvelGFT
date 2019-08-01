package br.com.gft.marvel.feature.home.data.service

import br.com.gft.data.network.NetworkEndpoints.Companion.GET_CHARACTERES_LIST
import br.com.gft.marvel.feature.home.data.service.model.CharacterResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET(GET_CHARACTERES_LIST)
    fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Flowable<CharacterResponse>
}