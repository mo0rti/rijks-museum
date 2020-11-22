package com.orangeocean.rijksmuseum.data.datasource.cache.request

import com.orangeocean.rijksmuseum.domain.model.Request

interface RequestCacheDataSource {

    suspend fun insert(request: Request): Long

    suspend fun updateToSynced(id: Long)

    suspend fun getUnSynced(): List<Request>

    suspend fun getById(id: Long): Request
}