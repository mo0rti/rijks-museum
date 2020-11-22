package com.orangeocean.rijksmuseum.service.cache.artobject

import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity

interface ArtObjectCacheService {

    suspend fun insert(artObjectCacheEntity: ArtObjectCacheEntity): Long

    suspend fun get(artistName: String): List<ArtObjectCacheEntity>

}