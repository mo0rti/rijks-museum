package com.orangeocean.rijksmuseum.network

import com.orangeocean.rijksmuseum.network.entity.ArtCollectionNetworkEntity
import retrofit2.http.GET

interface IArtCollectionRetrofit {
    @GET("collection?key=yW6uq3BV&involvedMaker=Rembrandt+van+Rijn")
    suspend fun get(): List<ArtCollectionNetworkEntity>
}