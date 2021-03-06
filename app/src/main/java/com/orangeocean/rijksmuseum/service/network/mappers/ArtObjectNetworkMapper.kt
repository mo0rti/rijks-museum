package com.orangeocean.rijksmuseum.service.network.mappers

import com.orangeocean.rijksmuseum.domain.common.EntityMapper
import com.orangeocean.rijksmuseum.domain.model.ArtObject
import com.orangeocean.rijksmuseum.service.network.entity.ArtObjectNetworkEntity
import com.orangeocean.rijksmuseum.service.network.entity.WebImageNetworkEntity
import java.util.*
import javax.inject.Inject

class ArtObjectNetworkMapper
@Inject
constructor(): EntityMapper<ArtObjectNetworkEntity, ArtObject> {
    override fun toDomainModel(entity: ArtObjectNetworkEntity): ArtObject {
        return ArtObject(
            id = entity.id,
            title = entity.title,
            thumbnail = entity.headerImage.url,
            imageUrl = entity.webImage.url,
            artistName = entity.principalOrFirstMaker,
            description = entity.description,
        )
    }

    override fun toEntity(domainModel: ArtObject): ArtObjectNetworkEntity {
        return ArtObjectNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            principalOrFirstMaker = domainModel.artistName,
            headerImage = WebImageNetworkEntity(
                guid = UUID.randomUUID().toString(),
                url = domainModel.thumbnail,
            ),
            webImage = WebImageNetworkEntity(
                guid = UUID.randomUUID().toString(),
                url = domainModel.imageUrl,
            ),
            description = domainModel.description,
        )
    }

    fun toDomainModelList(entities: List<ArtObjectNetworkEntity>): List<ArtObject> {
        return entities.map { toDomainModel(it) }
    }
}