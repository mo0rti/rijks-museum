package com.orangeocean.rijksmuseum.data.datasource.cache

import com.orangeocean.rijksmuseum.domain.model.Request

interface IRequestCacheDataSource {

    suspend fun insert(request: Request): Long

    suspend fun updateToSynced(id: Long)

    suspend fun getUnSynced(): List<Request>

    suspend fun getById(id: Long): Request
}