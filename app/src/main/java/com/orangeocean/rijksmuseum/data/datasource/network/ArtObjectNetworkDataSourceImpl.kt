package com.orangeocean.rijksmuseum.data.datasource.network

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.network.artcollection.ArtCollectionNetworkService
import com.orangeocean.rijksmuseum.service.network.mappers.ArtObjectNetworkMapper
import javax.inject.Inject

class ArtObjectNetworkDataSourceImpl
@Inject
constructor(
    private val networkService: ArtCollectionNetworkService,
    private val networkMapper: ArtObjectNetworkMapper
): ArtObjectNetworkDataSource {

    override suspend fun get(artistName: String): List<ArtObject> {
        val artCollection = networkService.get(artistName)
        return networkMapper.toDomainModelList(artCollection.artObjects)
    }
}