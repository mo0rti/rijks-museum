package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.network.artcollection.ArtCollectionNetworkService
import com.orangeocean.rijksmuseum.service.network.artcollection.IArtCollectionNetworkService
import com.orangeocean.rijksmuseum.service.network.mappers.ArtObjectNetworkMapper
import javax.inject.Inject

class ArtObjectNetworkDataSource
@Inject
constructor(
    private val networkService: IArtCollectionNetworkService,
    private val networkMapper: ArtObjectNetworkMapper
): IArtObjectNetworkDataSource {

    override suspend fun get(artistName: String): List<ArtObject> {
        val artCollection = networkService.get(artistName)
        return networkMapper.toDomainModelList(artCollection.artObjects)
    }
}