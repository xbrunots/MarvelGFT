package br.com.gft.marvel.feature.home.data.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UrlItem(
    @SerializedName("type") val type: String? = "",
    @SerializedName("url") val url: String? = ""
) : Serializable
