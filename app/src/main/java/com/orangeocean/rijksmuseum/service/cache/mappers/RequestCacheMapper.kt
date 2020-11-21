package com.orangeocean.rijksmuseum.service.cache.mappers

import com.orangeocean.rijksmuseum.domain.model.Request
import com.orangeocean.rijksmuseum.domain.utils.IEntityMapper
import com.orangeocean.rijksmuseum.service.cache.entity.RequestCacheEntity
import javax.inject.Inject

class RequestCacheMapper
@Inject
constructor() : IEntityMapper<RequestCacheEntity, Request> {
    override fun toDomainModel(entity: RequestCacheEntity): Request {
        return Request(
            id = entity.id,
            url = entity.url,
            syncPending = entity.syncPending,
        )
    }

    override fun toEntity(domainModel: Request): RequestCacheEntity {
        return RequestCacheEntity(
            id = domainModel.id,
            url = domainModel.url,
            syncPending = domainModel.syncPending,
        )
    }

    fun toDomainModelList(entities: List<RequestCacheEntity>) : List<Request> {
        return entities.map { toDomainModel(it) }
    }
}