package com.orangeocean.rijksmuseum.service.cache.artobject

import com.orangeocean.rijksmuseum.service.cache.entity.RequestCacheEntity

interface IRequestCacheService {

    suspend fun insert(requestCacheEntity: RequestCacheEntity): Long

    suspend fun updateToSynced(id: Long)

    suspend fun getUnSynced(): List<RequestCacheEntity>

    suspend fun getById(id: Long): RequestCacheEntity
}