package com.orangeocean.rijksmuseum.service.network.entity

import com.google.gson.annotations.SerializedName

data class WebImageNetworkEntity (
    @SerializedName("guid")
    var guid: String,

    @SerializedName("url")
    var url: String,
)