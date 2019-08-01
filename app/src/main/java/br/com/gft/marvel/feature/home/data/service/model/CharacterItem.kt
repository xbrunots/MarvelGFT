package br.com.gft.marvel.feature.home.data.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterItem(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("urls") val urls: List<UrlItem>? = listOf()
) : Serializable