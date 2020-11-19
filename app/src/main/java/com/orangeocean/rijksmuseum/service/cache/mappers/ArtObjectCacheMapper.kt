package com.orangeocean.rijksmuseum.service.cache.mappers

import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.domain.utils.IEntityMapper
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity
import javax.inject.Inject

class ArtObjectCacheMapper
@Inject
constructor() : IEntityMapper<ArtObjectCacheEntity, ArtObject> {
    override fun toDomainModel(entity: ArtObjectCacheEntity): ArtObject {
        return ArtObject(
            id = entity.id,
            title = entity.title,
            thumbnail = entity.thumbnail,
            imageUrl = entity.imageUrl,
            artistName = entity.artistName,
        )
    }

    override fun toEntity(domainModel: ArtObject): ArtObjectCacheEntity {
        return ArtObjectCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            thumbnail = domainModel.thumbnail,
            imageUrl = domainModel.imageUrl,
            artistName = domainModel.artistName,
        )
    }

    fun toDomainModelList(entities: List<ArtObjectCacheEntity>) : List<ArtObject> {
        return entities.map { toDomainModel(it) }
    }
}