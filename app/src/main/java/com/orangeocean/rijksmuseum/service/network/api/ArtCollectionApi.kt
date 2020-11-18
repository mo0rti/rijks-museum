package com.orangeocean.rijksmuseum.service.network.api

import com.orangeocean.rijksmuseum.service.network.entity.ArtCollectionNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtCollectionApi {
    @GET("nl/collection")
    suspend fun get(@Query("key") apiKey: String,
                    @Query("involvedMaker") artistName: String
    ): ArtCollectionNetworkEntity
}