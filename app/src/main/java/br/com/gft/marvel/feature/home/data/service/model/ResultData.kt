package br.com.gft.marvel.feature.home.data.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultData(
    @SerializedName("offset") val offset: Int? = 0,
    @SerializedName("limit") val limit: Int? = 0,
    @SerializedName("total") val total: Int? = 0,
    @SerializedName("count") val count: Int? = 0,
    @SerializedName("results") val results: List<CharacterItem>
) : Serializable