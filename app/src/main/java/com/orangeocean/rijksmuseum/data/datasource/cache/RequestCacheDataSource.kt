package com.orangeocean.rijksmuseum.data.datasource.cache

import com.orangeocean.rijksmuseum.domain.model.Request
import com.orangeocean.rijksmuseum.service.cache.artobject.RequestCacheService
import com.orangeocean.rijksmuseum.service.cache.mappers.RequestCacheMapper

class RequestCacheDataSource
constructor(
    private val cacheService: RequestCacheService,
    private val cacheMapper: RequestCacheMapper
): IRequestCacheDataSource {

    override suspend fun insert(request: Request): Long {
        return cacheService.insert(cacheMapper.toEntity(request))
    }

    override suspend fun updateToSynced(id: Long) {
        cacheService.updateToSynced(id)
    }

    override suspend fun getUnSynced(): List<Request> {
        return cacheMapper.toDomainModelList(cacheService.getUnSynced())
    }

    override suspend fun getById(id: Long): Request {
        return cacheMapper.toDomainModel(cacheService.getById(id))
    }
}
