package br.com.gft.marvel.feature.home.data.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterResponse(
    @SerializedName("code") val code: Int? = 401,
    @SerializedName("status") val status: String? = "",
    @SerializedName("etag") val etag: String? = "",
    @SerializedName("data") var data: ResultData
) : Serializable
