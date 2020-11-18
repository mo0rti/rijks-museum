package com.orangeocean.rijksmuseum.data.datasource.cache

import com.orangeocean.rijksmuseum.domain.model.ArtObject

interface IArtObjectCacheDataSource {

    suspend fun insert(artObject: ArtObject): Long

    suspend fun get(artistName: String): List<ArtObject>
}