package com.orangeocean.rijksmuseum.network.entity

import com.google.gson.annotations.SerializedName

data class ArtObjectNetworkEntity(
    @SerializedName("id")
    var id: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("webImage")
    var webImage: WebImageNetworkEntity,
)