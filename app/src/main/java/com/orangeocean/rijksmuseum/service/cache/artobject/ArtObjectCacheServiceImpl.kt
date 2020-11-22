package com.orangeocean.rijksmuseum.service.cache.artobject

import com.orangeocean.rijksmuseum.service.cache.database.ArtObjectDao
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity
import javax.inject.Inject

class ArtObjectCacheServiceImpl
@Inject
constructor(
    private val artObjectDao: ArtObjectDao
): ArtObjectCacheService {

    override suspend fun insert(artObjectCacheEntity: ArtObjectCacheEntity): Long {
        return artObjectDao.insert(artObjectCacheEntity)
    }

    override suspend fun get(artistName: String): List<ArtObjectCacheEntity> {
        return artObjectDao.get(artistName)
    }
}