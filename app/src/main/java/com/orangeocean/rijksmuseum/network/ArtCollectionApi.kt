package com.orangeocean.rijksmuseum.network

import com.orangeocean.rijksmuseum.network.entity.ArtCollectionNetworkEntity
import retrofit2.http.GET

interface ArtCollectionApi {
    @GET("nl/collection?key=yW6uq3BV&involvedMaker=Rembrandt+van+Rijn")
    suspend fun get(): ArtCollectionNetworkEntity
}