package com.orangeocean.rijksmuseum.service.network.artcollection

import com.orangeocean.rijksmuseum.service.network.entity.ArtCollectionNetworkEntity

interface ArtCollectionNetworkService {

    suspend fun get(artistName: String): ArtCollectionNetworkEntity
}