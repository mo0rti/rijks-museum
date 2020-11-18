package com.orangeocean.rijksmuseum.service.network.entity

import com.google.gson.annotations.SerializedName

data class ArtObjectNetworkEntity(
    @SerializedName("id")
    var id: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("principalOrFirstMaker")
    var principalOrFirstMaker: String,

    @SerializedName("headerImage")
    var headerImage: WebImageNetworkEntity,

    @SerializedName("webImage")
    var webImage: WebImageNetworkEntity,
)