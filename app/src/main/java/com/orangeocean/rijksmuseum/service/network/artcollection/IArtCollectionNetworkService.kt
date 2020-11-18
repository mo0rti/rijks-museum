package com.orangeocean.rijksmuseum.service.network.artcollection

import com.orangeocean.rijksmuseum.service.network.entity.ArtCollectionNetworkEntity

interface IArtCollectionNetworkService {

    suspend fun get(artistName: String): ArtCollectionNetworkEntity
}