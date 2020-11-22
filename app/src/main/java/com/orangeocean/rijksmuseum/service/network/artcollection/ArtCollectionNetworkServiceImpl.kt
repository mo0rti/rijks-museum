package com.orangeocean.rijksmuseum.service.network.artcollection

import com.orangeocean.rijksmuseum.service.network.api.ArtCollectionApi
import com.orangeocean.rijksmuseum.service.network.entity.ArtCollectionNetworkEntity
import com.orangeocean.rijksmuseum.utils.Constants
import javax.inject.Inject

class ArtCollectionNetworkServiceImpl
@Inject
constructor(
    private val artCollectionApi: ArtCollectionApi
): ArtCollectionNetworkService {

    override suspend fun get(artistName: String): ArtCollectionNetworkEntity {
        return artCollectionApi.get(Constants.API_KEY, artistName)
    }
}