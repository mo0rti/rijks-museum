package com.orangeocean.rijksmuseum.network.entity

import com.google.gson.annotations.SerializedName

data class ArtCollectionNetworkEntity(
    @SerializedName("count")
    var count: Int,

    @SerializedName("artObjects")
    var artObjects: List<ArtObjectNetworkEntity>,
)