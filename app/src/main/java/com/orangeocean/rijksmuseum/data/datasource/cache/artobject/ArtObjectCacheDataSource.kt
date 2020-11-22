package com.orangeocean.rijksmuseum.data.datasource.cache.artobject

import com.orangeocean.rijksmuseum.domain.model.ArtObject

interface ArtObjectCacheDataSource {

    suspend fun insert(artObject: ArtObject): Long

    suspend fun get(artistName: String): List<ArtObject>
}