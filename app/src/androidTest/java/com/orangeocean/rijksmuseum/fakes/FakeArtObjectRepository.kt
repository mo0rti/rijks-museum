package com.orangeocean.rijksmuseum.fakes

import com.orangeocean.rijksmuseum.service.cache.database.ArtObjectDao
import com.orangeocean.rijksmuseum.service.cache.mappers.ArtObjectCacheMapper
import com.orangeocean.rijksmuseum.service.network.api.ArtCollectionApi
import com.orangeocean.rijksmuseum.service.network.mappers.ArtObjectNetworkMapper
import com.orangeocean.rijksmuseum.data.repository.ArtObjectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeArtObjectRepository
@Inject
constructor(
    artObjectDao: ArtObjectDao,
    artCollectionApi: ArtCollectionApi,
    artObjectNetworkMapper: ArtObjectNetworkMapper,
    artObjectCacheMapper: ArtObjectCacheMapper
) : ArtObjectRepository(
    artObjectDao,
    artCollectionApi,
    artObjectNetworkMapper,
    artObjectCacheMapper
) {

}