package com.orangeocean.rijksmuseum.service.cache.artobject

import com.orangeocean.rijksmuseum.service.cache.database.RequestDao
import com.orangeocean.rijksmuseum.service.cache.entity.RequestCacheEntity

class RequestCacheServiceImpl
constructor(
    private val requestDao: RequestDao
): RequestCacheService {
    override suspend fun insert(requestCacheEntity: RequestCacheEntity): Long {
       return requestDao.insert(requestCacheEntity)
    }

    override suspend fun updateToSynced(id: Long) {
        requestDao.updateToSynced(id)
    }

    override suspend fun getUnSynced(): List<RequestCacheEntity> {
        return requestDao.getUnSynced()
    }

    override suspend fun getById(id: Long): RequestCacheEntity {
        return requestDao.getById(id)
    }
}