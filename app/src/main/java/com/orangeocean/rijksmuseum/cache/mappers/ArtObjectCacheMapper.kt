package com.orangeocean.rijksmuseum.cache.mappers

import com.orangeocean.rijksmuseum.cache.entity.ArtObjectCacheEntity
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.utils.IEntityMapper
import javax.inject.Inject

class ArtObjectCacheMapper
@Inject
constructor() : IEntityMapper<ArtObjectCacheEntity, ArtObject> {
    override fun toDomainModel(entity: ArtObjectCacheEntity): ArtObject {
        return ArtObject(
            id = entity.id,
            title = entity.title,
            imageUrl = entity.imageUrl,
        )
    }

    override fun toEntity(domainModel: ArtObject): ArtObjectCacheEntity {
        return ArtObjectCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            imageUrl = domainModel.imageUrl,
        )
    }

    fun toDomainModelList(entities: List<ArtObjectCacheEntity>) : List<ArtObject> {
        return entities.map { toDomainModel(it) }
    }
}